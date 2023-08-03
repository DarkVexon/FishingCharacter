package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.FishingMod.makeID;
import static theFishing.cardmods.StickerManager.DIFF_X;
import static theFishing.cardmods.StickerManager.DIFF_Y;

public class StickerEtherealMod extends AbstractCardModifier {
    public static String ID = makeID(StickerEtherealMod.class.getSimpleName());

    private float offsetX;
    private float offsetY;

    public StickerEtherealMod() {
        offsetX = MathUtils.random(-DIFF_X, DIFF_X);
        offsetY = MathUtils.random(DIFF_Y);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, StickerManager.StickerType.ETHEREAL, card, offsetX, offsetY);
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.isEthereal = true;
    }

    public void onRemove(AbstractCard card) {
        card.isEthereal = false;
    }

    public AbstractCardModifier makeCopy() {
        return new StickerEtherealMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
