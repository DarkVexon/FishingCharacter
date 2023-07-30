package theFishing.boards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

public class BoardEffect {

    private static final Texture bgTex = TexLoader.getTexture("fishingResources/images/board/bg.png");
    private static final float SIZE = 64F * Settings.scale;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("fishing:BoardEffects");

    public String description;
    private Runnable onEnter;

    public BoardEffect(String description, Runnable onEnter) {
        this.description = description;
        this.onEnter = onEnter;
    }

    public void activate() {
        onEnter.run();
    }

    public void render(SpriteBatch sb, int pos, float x, float y) {
        sb.draw(bgTex, x, y); //TODO: Scale
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, String.valueOf(pos), x + (SIZE / 2), y + (SIZE / 2));
    }

    public void update(int pos, float x, float y) {
        if (InputHelper.mX >= x && InputHelper.mX < SIZE && InputHelper.mY >= y && InputHelper.mY < y + SIZE) {
            ImageHelper.tipBoxAtMousePos(uiStrings.TEXT[0] + pos, description);
        }
    }
}
