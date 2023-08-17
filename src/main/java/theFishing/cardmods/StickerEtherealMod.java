package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theFishing.FishingMod.makeID;

public class StickerEtherealMod extends AbstractStickerModifier {
    public static String ID = makeID(StickerEtherealMod.class.getSimpleName());

    public StickerEtherealMod() {
        super(StickerManager.StickerType.ETHEREAL);
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
