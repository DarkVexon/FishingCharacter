package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.basefish.*;
import theFishing.cards.fish.maelstrom.TheWhale;
import theFishing.relics.MaelstromAnkh;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static theFishing.FishingMod.makeID;

public abstract class AbstractFishCard extends AbstractFishingCard {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("FishDescriptor"));

    public AbstractFishCard(String cardID, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(cardID, 0, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
        exhaust = true;
        baseThirdMagic = thirdMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        fishEffect(p, m);
        addToBot(new DrawCardAction(thirdMagic));
    }

    public abstract void fishEffect(AbstractPlayer p, AbstractMonster m);

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
            weightedFishList.put(Piranha.ID, 33);
            weightedFishList.put(Guppy.ID, 25);
            weightedFishList.put(Octopus.ID, 8);
            weightedFishList.put(Eel.ID, 8);
            weightedFishList.put(Boot.ID, 8);
            weightedFishList.put(Hammerhead.ID, 4);
            weightedFishList.put(Maw.ID, 3);
            weightedFishList.put(Shark.ID, 3);
            weightedFishList.put(Starfish.ID, 3);
            weightedFishList.put(Swordfish.ID, 2);
            weightedFishList.put(Qwilfish.ID, 1);
            weightedFishList.put(Jellyfish.ID, 1);
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
            maelstromFishList.put(Octopus.ID, 15);
            maelstromFishList.put(Eel.ID, 15);
            maelstromFishList.put(Hammerhead.ID, 15);
            maelstromFishList.put(Swordfish.ID, 15);
            maelstromFishList.put(Maw.ID, 15);
            maelstromFishList.put(Shark.ID, 5);
            maelstromFishList.put(Starfish.ID, 5);
            maelstromFishList.put(Qwilfish.ID, 5);
            maelstromFishList.put(Jellyfish.ID, 5);
            maelstromFishList.put(SeaMonster.ID, 4);
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

    @Override
    public void upp() {
        upgradeThirdMagic(1);
        System.out.println(getClass().getSimpleName());
        uDesc();
    }
}
