package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.FishingMod.makeID;
import static theFishing.cardmods.StickerManager.DIFF_X;
import static theFishing.cardmods.StickerManager.DIFF_Y;

public class StickerRetainMod extends AbstractCardModifier {
    public static String ID = makeID(StickerRetainMod.class.getSimpleName());

    private float offsetX;
    private float offsetY;

    public StickerRetainMod() {
        offsetX = MathUtils.random(-DIFF_X, DIFF_X);
        offsetY = MathUtils.random(DIFF_Y);
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
