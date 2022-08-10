package theFishing.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.basefish.*;
import theFishing.cards.fish.maelstrom.*;
import theFishing.relics.MaelstromAnkh;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static theFishing.FishingMod.makeID;

public abstract class AbstractFishCard extends AbstractFishingCard {

    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("FishDescriptor"));

    public AbstractFishCard(String cardID, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(cardID, 0, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
        exhaust = true;
    }

    public static LinkedHashMap<String, Integer> weightedFishList;
    public static LinkedHashMap<String, Integer> maelstromFishList;

    public static AbstractCard returnRandomFish() {
        if (AbstractDungeon.player.hasRelic(MaelstromAnkh.ID)) {
            return returnRandomMaelstromFish();
        }
        return returnRandomBasicFish();
    }

    private static AbstractCard returnRandomBasicFish() {
        if (weightedFishList == null) {
            weightedFishList = new LinkedHashMap<>();
            weightedFishList.put(Guppy.ID, 33);
            weightedFishList.put(Piranha.ID, 30);
            weightedFishList.put(Octopus.ID, 7);
            weightedFishList.put(Eel.ID, 7);
            weightedFishList.put(Boot.ID, 6);
            weightedFishList.put(Maw.ID, 4);
            weightedFishList.put(Hammerhead.ID, 4);
            weightedFishList.put(Shark.ID, 3);
            weightedFishList.put(Starfish.ID, 3);
            weightedFishList.put(Qwilfish.ID, 2);
            weightedFishList.put(SeaMonster.ID, 1);
        }

        int fishRoll = AbstractDungeon.cardRandomRng.random(1,
                weightedFishList.keySet().stream()
                        .mapToInt(f -> weightedFishList.get(f))
                        .sum()
        );

        for (String fishy : weightedFishList.keySet()) {
            fishRoll -= weightedFishList.get(fishy);
            if (fishRoll <= 0)
                return CardLibrary.getCard(fishy).makeCopy();
        }

        return new Madness();
    }

    private static AbstractCard returnRandomMaelstromFish() {
        if (maelstromFishList == null) {
            maelstromFishList = new LinkedHashMap<>();
            maelstromFishList.put(CeramicFish.ID, 18);
            maelstromFishList.put(Maw.ID, 18);
            maelstromFishList.put(Swordfish.ID, 10);
            maelstromFishList.put(Hammerhead.ID, 10);
            maelstromFishList.put(Jellyfish.ID, 10);
            maelstromFishList.put(Starfy.ID, 8);
            maelstromFishList.put(Shark.ID, 6);
            maelstromFishList.put(Starfish.ID, 6);
            maelstromFishList.put(Qwilfish.ID, 6);
            maelstromFishList.put(SeaMonster.ID, 3);
            maelstromFishList.put(FlyingFish.ID, 2);
            maelstromFishList.put(Blooper.ID, 2);
            maelstromFishList.put(TheWhale.ID, 1);
        }

        int fishRoll = AbstractDungeon.cardRandomRng.random(1,
                maelstromFishList.keySet().stream()
                        .mapToInt(f -> maelstromFishList.get(f))
                        .sum()
        );

        for (String fishy : maelstromFishList.keySet()) {
            fishRoll -= maelstromFishList.get(fishy);
            if (fishRoll <= 0)
                return CardLibrary.getCard(fishy).makeCopy();
        }

        return new Madness();
    }

    @Override
    public List<String> getCardDescriptors() {
        return Collections.singletonList(uiStrings.TEXT[0]);
    }

    public void trickyInitializeTitle() {
        initializeTitle();
    }
}
