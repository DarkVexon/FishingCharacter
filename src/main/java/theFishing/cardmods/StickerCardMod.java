package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class StickerCardMod extends AbstractCardModifier {

    public static final String ID = FishingMod.makeID(StickerCardMod.class.getSimpleName());
    private static final Texture stickerTex = TexLoader.getTexture("fishingResources/images/ui/sticker.png");
    private static final TextureAtlas.AtlasRegion stickerAtlas = ImageHelper.asAtlasRegion(stickerTex);

    private static final float DIFF_X = 100;
    private static final float DIFF_Y = 150;

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
        renderHelper(sb, stickerAtlas, card.current_x, card.current_y, card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StickerCardMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    private void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard c) {
        Color color = Color.WHITE.cpy();
        color.a = c.transparency;
        sb.setColor(color);
        sb.draw(img, drawX + (offsetX * Settings.scale * c.drawScale) + img.offsetX - (float) img.originalWidth / 2.0F, drawY + (offsetY * Settings.scale * c.drawScale) + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
    }
}
