package theFishing.boards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.util.TexLoader;

public class BoardEffect {
    public String description;
    private Runnable onEnter;

    public BoardEffect(String description, Runnable onEnter) {
        this.description = description;
        this.onEnter = onEnter;
    }

    public void activate() {
        onEnter.run();
    }

//    public void render(SpriteBatch sb, int pos, float x, float y) {
//        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, description, x, y);
//    }
}
