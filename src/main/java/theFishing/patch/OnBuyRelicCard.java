package theFishing.patch;

import com.megacrit.cardcrawl.relics.AbstractRelic;

public interface OnBuyRelicCard {
    void onBuyRelic(AbstractRelic r, int price);
}
