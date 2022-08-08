package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theFishing.cards.AbstractFishingCard;

public abstract class AbstractBoxTopper extends AbstractFishingCard {
    public AbstractBoxTopper(String cardID, int cost, AbstractCard.CardType type, AbstractCard.CardTarget target) {
        super(cardID, cost, type, CardRarity.SPECIAL, target, CardColor.COLORLESS);
    }
}
