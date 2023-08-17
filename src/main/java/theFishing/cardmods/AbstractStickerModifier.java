package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.cardmods.StickerManager.DIFF_X;
import static theFishing.cardmods.StickerManager.DIFF_Y;

public abstract class AbstractStickerModifier extends AbstractCardModifier {

    private StickerManager.StickerType type;
    public float offsetX;
    public float offsetY;

    public AbstractStickerModifier(StickerManager.StickerType type) {
        this.type = type;
        offsetX = MathUtils.random(-DIFF_X, DIFF_X);
        offsetY = MathUtils.random(DIFF_Y);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, type, card, offsetX, offsetY);
    }
}
