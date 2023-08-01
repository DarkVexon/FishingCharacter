package theFishing.boards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import theFishing.FishingMod;
import theFishing.util.TexLoader;

import java.util.ArrayList;

public abstract class AbstractBoard {

    private static final Texture arrow = TexLoader.getTexture("fishingResources/images/board/player.png");
    private static final float POS_X = 300F * Settings.scale;
    private static final float DIST_BETWEEN = 100F * Settings.scale;
    private static final float POS_Y = Settings.HEIGHT / 3F;

    public String id;
    public String name;
    public int progress = 0;
    public ArrayList<BoardEffect> effects;

    public AbstractBoard(String ID, String name) {
        this.name = name;
        this.id = ID;
    }

    public static AbstractBoard getRunBoard() {
        //TODO: Date-based system
        return new ChampsArena();
    }

    public void proceed() {
        progress += 1;
        if (progress >= effects.size()) {
            progress = 1;
        }
        effects.get(progress).activate();
    }

    public void reset() {
        progress = 0;
        FishingMod.timesCompletedThisCombat = 0;
    }

    public void render(SpriteBatch sb) {
        sb.draw(getBackground(), POS_X, POS_Y);
        sb.draw(arrow, POS_X, POS_Y + (DIST_BETWEEN * progress));
    }

    public abstract Texture getBackground();

    public static AbstractBoard getBoardByID(String ID) {
        switch (ID) {
            case ChampsArena.ID:
                return new ChampsArena();
            default:
                return new ChampsArena();
        }
    }
}
