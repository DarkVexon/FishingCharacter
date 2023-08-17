package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.FishingMod.makeID;

public class StickerExhaustMod extends AbstractStickerModifier {
    public static String ID = makeID(StickerExhaustMod.class.getSimpleName());

    public StickerExhaustMod() {
        super(StickerManager.StickerType.EXHAUST);
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, StickerManager.StickerType.EXHAUST, card, offsetX, offsetY);
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.exhaust;
    }

    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
    }

    public void onRemove(AbstractCard card) {
        card.exhaust = false;
    }

    public AbstractCardModifier makeCopy() {
        return new StickerExhaustMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
