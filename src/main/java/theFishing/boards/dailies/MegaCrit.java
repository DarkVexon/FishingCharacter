package theFishing.boards.dailies;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cardmods.StickerRetainMod;

import static theFishing.util.Wiz.makeInHandTop;

public class MegaCrit extends AbstractBoard {
    public static final String ID = FishingMod.makeID(MegaCrit.class.getSimpleName());

    public MegaCrit() {
        super(ID);
    }

    @Override
    public void effect() {
        AbstractCard toMake = generateCardChoices();
        CardModifierManager.addModifier(toMake, new StickerRetainMod());
        makeInHandTop(toMake);
    }

    private AbstractCard generateCardChoices() {
        int roll = AbstractDungeon.cardRandomRng.random(99);
        AbstractCard.CardRarity cardRarity;
        if (roll < 55) {
            cardRarity = AbstractCard.CardRarity.COMMON;
        } else if (roll < 85) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            cardRarity = AbstractCard.CardRarity.RARE;
        }

        AbstractCard tmp = CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
        return tmp.makeCopy();
    }
}
