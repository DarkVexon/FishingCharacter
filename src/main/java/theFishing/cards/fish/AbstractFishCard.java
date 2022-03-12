package theFishing.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.basefish.*;
import theFishing.cards.fish.maelstrom.*;
import theFishing.relics.MaelstromAnkh;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractFishCard extends AbstractFishingCard {

    public AbstractFishCard(String cardID, int cost, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(cardID, cost, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
    }

    public static LinkedHashMap<AbstractFishCard, Integer> weightedFishList;
    public static LinkedHashMap<AbstractFishCard, Integer> maelstromFishList;

    public static AbstractCard returnRandomFish() {
        if (AbstractDungeon.player.hasRelic(MaelstromAnkh.ID)) {
            return returnRandomMaelstromFish();
        }
        return returnRandomBasicFish();
    }

    private static AbstractCard returnRandomBasicFish() {
        if (weightedFishList == null) {
            weightedFishList = new LinkedHashMap<>();
            weightedFishList.put(new Guppy(), 30);
            weightedFishList.put(new Piranha(), 30);
            weightedFishList.put(new Octopus(), 6);
            weightedFishList.put(new Clownfish(), 6);
            weightedFishList.put(new Eel(), 5);
            weightedFishList.put(new Shark(), 4);
            weightedFishList.put(new Siren(), 4);
            weightedFishList.put(new Boot(), 4);
            weightedFishList.put(new Maw(), 3);
            weightedFishList.put(new Hammerhead(), 3);
            weightedFishList.put(new Orca(), 2);
            weightedFishList.put(new Qwilfish(), 2);
            weightedFishList.put(new SeaMonster(), 1);
        }

        int fishRoll = AbstractDungeon.cardRandomRng.random(1,
                weightedFishList.keySet().stream()
                        .mapToInt(f -> weightedFishList.get(f))
                        .sum()
        );

        for (AbstractFishCard fishy : weightedFishList.keySet()) {
            fishRoll -= weightedFishList.get(fishy);
            if (fishRoll <= 0)
                return fishy.makeCopy();
        }

        return new Madness();
    }

    private static AbstractCard returnRandomMaelstromFish() {
        if (maelstromFishList == null) {
            maelstromFishList = new LinkedHashMap<>();
            maelstromFishList.put(new Maw(), 15);
            maelstromFishList.put(new Jellyfish(), 13);
            maelstromFishList.put(new Eel(), 11);
            maelstromFishList.put(new Clownfish(), 10);
            maelstromFishList.put(new Hammerhead(), 10);
            maelstromFishList.put(new Qwilfish(), 10);
            maelstromFishList.put(new Swordfish(), 10);
            maelstromFishList.put(new Shark(), 5);
            maelstromFishList.put(new Siren(), 5);
            maelstromFishList.put(new SeaMonster(), 4);
            maelstromFishList.put(new FlyingFish(), 4);
            maelstromFishList.put(new Rainbowscales(), 2);
            maelstromFishList.put(new Neow(), 1);
        }

        int fishRoll = AbstractDungeon.cardRandomRng.random(1,
                maelstromFishList.keySet().stream()
                        .mapToInt(f -> maelstromFishList.get(f))
                        .sum()
        );

        for (AbstractFishCard fishy : maelstromFishList.keySet()) {
            fishRoll -= maelstromFishList.get(fishy);
            if (fishRoll <= 0)
                return fishy.makeCopy();
        }

        return new Madness();
    }

    @Override
    public List<String> getCardDescriptors() {
        return Collections.singletonList("Fish");
    }
}
