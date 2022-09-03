package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public interface OnBuyRelicCard {
    void onBuyRelic(AbstractRelic r, int price);
}
