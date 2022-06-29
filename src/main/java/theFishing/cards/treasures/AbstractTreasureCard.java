package theFishing.cards.treasures;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.AbstractFishingCard;

import java.util.ArrayList;

public abstract class AbstractTreasureCard extends AbstractFishingCard {

    public AbstractTreasureCard(String cardID, int cost, CardType type, CardTarget target) {
        super(cardID, cost, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
    }

    public static ArrayList<AbstractCard> treasuresList;

    public static AbstractCard returnRandomLegendaryTreasure() {
        if (treasuresList == null) {
            treasuresList = new ArrayList<>();
            treasuresList.add(new ComicallyLargeSpoon());
            treasuresList.add(new DefinitelyUntrappedIdol());
            treasuresList.add(new GoldenLute());
            treasuresList.add(new HolyGrail());
            treasuresList.add(new OneRing());
            treasuresList.add(new PastKillingGun());
            treasuresList.add(new PowerEternal());
            treasuresList.add(new Rhaast());
        }

        return treasuresList.get(AbstractDungeon.cardRandomRng.random(treasuresList.size() - 1)).makeCopy();
    }
}
