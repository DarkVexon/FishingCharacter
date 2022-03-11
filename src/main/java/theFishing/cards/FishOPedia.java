package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.fish.AbstractFishCard;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.makeInHand;

public class FishOPedia extends AbstractFishingCard {
    public final static String ID = makeID("FishOPedia");
    // intellij stuff skill, self, , , , , , 

    public FishOPedia() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<String> fishes = new ArrayList<>();
        for (AbstractCard q : AbstractFishCard.weightedFishList.keySet()) {
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().noneMatch(c -> c.cardID.equals(q.cardID))) {
                fishes.add(q.cardID);
            }
        }
        if (fishes.isEmpty()) {
            applyToSelf(new StrengthPower(p, 4));
        } else {
            makeInHand(CardLibrary.getCard(fishes.get(AbstractDungeon.cardRandomRng.random(fishes.size() - 1))));
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}