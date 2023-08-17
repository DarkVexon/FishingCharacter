package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.FishingMod.makeID;

public class StickerRetainMod extends AbstractStickerModifier {
    public static String ID = makeID(StickerRetainMod.class.getSimpleName());

    public StickerRetainMod() {
        super(StickerManager.StickerType.RETAIN);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, StickerManager.StickerType.RETAIN, card, offsetX, offsetY);
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.selfRetain;
    }

    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }

    public void onRemove(AbstractCard card) {
        card.selfRetain = false;
    }

    public AbstractCardModifier makeCopy() {
        return new StickerRetainMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
