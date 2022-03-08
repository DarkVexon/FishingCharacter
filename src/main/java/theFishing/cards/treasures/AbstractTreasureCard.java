package theFishing.cards.treasures;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.fish.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class AbstractTreasureCard extends AbstractFishingCard {

    public AbstractTreasureCard(String cardID, int cost, CardType type, CardTarget target) {
        super(cardID, cost, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
    }

    public static ArrayList<AbstractCard> treasuresList;

    public static AbstractCard returnRandomLegendaryTreasure() {
        if (treasuresList == null) {
            treasuresList = new ArrayList<>();
            //add cards
        }

        return treasuresList.get(AbstractDungeon.cardRandomRng.random(treasuresList.size())).makeCopy();
    }
}
