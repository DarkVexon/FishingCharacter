package theFishing.boards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

public abstract class AbstractBoard {

    private static final float POS_X = 300F * Settings.scale;
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

    public void render(SpriteBatch sb) {

    }

    public void update() {

    }
}
