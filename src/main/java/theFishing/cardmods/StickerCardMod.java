package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

    private static void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard C) {
        Color color = Color.WHITE.cpy();
        color.a = C.transparency;
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, C.drawScale * Settings.scale, C.drawScale * Settings.scale, C.angle);
    }
}
