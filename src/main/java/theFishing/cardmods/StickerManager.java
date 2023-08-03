package theFishing.cardmods;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

public class StickerManager {
    private static final Texture hpLossStickerTex = TexLoader.getTexture("fishingResources/images/ui/sticker.png");
    private static final TextureAtlas.AtlasRegion hpLossStickerAtlas = ImageHelper.asAtlasRegion(hpLossStickerTex);

    private static final Texture etherealStickerTex = TexLoader.getTexture("fishingResources/images/ui/sticker_ethereal.png");
    private static final TextureAtlas.AtlasRegion etherealStickerAtlas = ImageHelper.asAtlasRegion(etherealStickerTex);

    private static final Texture exhaustStickerTex = TexLoader.getTexture("fishingResources/images/ui/sticker_exhaust.png");
    private static final TextureAtlas.AtlasRegion exhaustStickerAtlas = ImageHelper.asAtlasRegion(exhaustStickerTex);

    public static final float DIFF_X = 100;
    public static final float DIFF_Y = 150;

    public static void renderSticker(SpriteBatch sb, StickerType type, AbstractCard c, float offsetX, float offsetY) {
        TextureAtlas.AtlasRegion toUse;
        switch (type) {
            case ETHEREAL:
                toUse = etherealStickerAtlas;
                break;
            case EXHAUST:
                toUse = exhaustStickerAtlas;
                break;
            case HPLOSS:
            default:
                toUse = hpLossStickerAtlas;
                break;
        }
        renderHelper(sb, toUse, c.current_x, c.current_y, c, offsetX, offsetY);
    }

    private static void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard c, float offsetX, float offsetY) {
        Color color = Color.WHITE.cpy();
        color.a = c.transparency;
        sb.setColor(color);
        sb.draw(img, drawX + (offsetX * Settings.scale * c.drawScale) + img.offsetX - (float) img.originalWidth / 2.0F, drawY + (offsetY * Settings.scale * c.drawScale) + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, c.drawScale * Settings.scale, c.drawScale * Settings.scale, c.angle);
    }

    public enum StickerType {
        HPLOSS,
        ETHEREAL,
        EXHAUST
    }
}
