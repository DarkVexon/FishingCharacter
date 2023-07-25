package theFishing.util;

import basemod.patches.whatmod.WhatMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.cards.AbstractFishingCard;

import java.util.ArrayList;
import java.util.LinkedList;

public class ExportSheet {

    private static int rarityAsInt(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case BASIC:
                return 0;
            case SPECIAL:
                return 4;
            case COMMON:
                return 1;
            case UNCOMMON:
                return 2;
            case RARE:
                return 3;
            default:
                return 5;
        }
    }

    private static String upgradeAdjustedCost(AbstractCard card) {
        String result = String.valueOf(card.cost);
        int origCost = card.cost;
        card.upgrade();
        if (card.cost != origCost) {
            result += "(" + card.cost + ")";
        }
        return result;
    }

    private static String getCorrectlyCreatedDescription(AbstractCard card) {
        String result = card.rawDescription;
        result.replaceAll("!D!", String.valueOf(card.baseDamage));
        result.replaceAll("!B!", String.valueOf(card.baseBlock));
        result.replaceAll("!M!", String.valueOf(card.baseMagicNumber));
        if (card instanceof AbstractFishingCard) {
            result.replaceAll("!fishing:m2!", String.valueOf(((AbstractFishingCard) card).baseSecondMagic));
            result.replaceAll("!fishing:m3!", String.valueOf(((AbstractFishingCard) card).baseThirdMagic));
        }
        return result;
    }

    private static String upgradeAdjustedText(AbstractCard card) {
        String result = getCorrectlyCreatedDescription(card);
        card.upgrade();
        String newResult = getCorrectlyCreatedDescription(card);
        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(result, newResult, true);
        String finalResult = "";
        for (DiffMatchPatch.Diff d : diffs) {
            if (d.operation == DiffMatchPatch.Operation.INSERT) {
                finalResult += "(" + d.text + ")";
            } else if (d.operation == DiffMatchPatch.Operation.DELETE) {
                finalResult += ")" + d.text + "(";
            } else {
                finalResult += d.text;
            }
        }
        return finalResult;
    }

    public static String export() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID,Name,Type,Rarity,Cost,Effect\n");
        int coloridx = 1;
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (WhatMod.findModID(q.getClass()) != null)
                if (WhatMod.findModID(q.getClass()).equals(FishingMod.modID)) {
                    if (q.color == TheFishing.Enums.FISHING_COLOR) {
                        builder.append(coloridx);
                        builder.append(",");
                        builder.append(q.name);
                        builder.append(",");
                        builder.append(q.type.name());
                        builder.append(",");
                        builder.append(rarityAsInt(q.rarity) + "-" + q.rarity.name());
                        builder.append(",");
                        builder.append(upgradeAdjustedCost(q));
                        builder.append(",");
                        builder.append(upgradeAdjustedText(q));
                        builder.append("\n");
                        coloridx++;
                    }
                }
        }
        if (coloridx <= 75) {
            while (coloridx <= 75) {
                builder.append(coloridx).append(",,,,,\n");
                coloridx++;
            }
        }
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (WhatMod.findModID(q.getClass()).equals(FishingMod.modID)) {
                if (q.color != TheFishing.Enums.FISHING_COLOR) {
                    builder.append(",");
                    builder.append(q.name);
                    builder.append(",");
                    builder.append(q.type.name());
                    builder.append(",");
                    builder.append(rarityAsInt(q.rarity) + "-" + q.rarity.name());
                    builder.append(",");
                    builder.append(upgradeAdjustedCost(q));
                    builder.append(",");
                    builder.append(upgradeAdjustedText(q));
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }
}
