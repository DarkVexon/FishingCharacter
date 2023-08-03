package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;

import static theFishing.cardmods.StickerManager.DIFF_X;
import static theFishing.cardmods.StickerManager.DIFF_Y;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class StickerCardMod extends AbstractCardModifier {
    public static final String ID = FishingMod.makeID(StickerCardMod.class.getSimpleName());

    private float offsetX;
    private float offsetY;

    public StickerCardMod() {
        offsetX = MathUtils.random(-DIFF_X, DIFF_X);
        offsetY = MathUtils.random(DIFF_Y);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                att(new LoseHPAction(AbstractDungeon.getRandomMonster(), AbstractDungeon.player, 8));
            }
        });
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, StickerManager.StickerType.HPLOSS, card, offsetX, offsetY);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StickerCardMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
