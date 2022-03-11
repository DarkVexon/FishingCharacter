package theFishing.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.AbstractFishingCard;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractFishCard extends AbstractFishingCard {

    public AbstractFishCard(String cardID, int cost, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(cardID, cost, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);

    }

    public static LinkedHashMap<AbstractFishCard, Integer> weightedFishList;

    public static AbstractCard returnRandomFish() {
        if (weightedFishList == null) {
            weightedFishList = new LinkedHashMap<>();
            weightedFishList.put(new Guppy(), 30);
            weightedFishList.put(new Piranha(), 25);
            weightedFishList.put(new Clownfish(), 10);
            weightedFishList.put(new Starfish(), 5);
            weightedFishList.put(new Octopus(), 5);
            weightedFishList.put(new Eel(), 4);
            weightedFishList.put(new Shark(), 4);
            weightedFishList.put(new Siren(), 4);
            weightedFishList.put(new Maw(), 3);
            weightedFishList.put(new Hammerhead(), 3);
            weightedFishList.put(new Orca(), 2);
            weightedFishList.put(new Qwilfish(), 2);
            weightedFishList.put(new Boot(), 2);
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

    @Override
    public List<String> getCardDescriptors() {
        return Collections.singletonList("Fish");
    }
}
