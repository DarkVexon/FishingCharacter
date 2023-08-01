package theFishing.boards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.FontHelper;
import theFishing.FishingMod;
import theFishing.boards.weeklies.ChampsArena;
import theFishing.boards.weeklies.TheLibrary;
import theFishing.boards.weeklies.TombOfRorrim;

import java.util.ArrayList;

public abstract class AbstractBoard {

//    private static final Texture arrow = TexLoader.getTexture("fishingResources/images/board/player.png");
//    private static final float POS_X = 300F * Settings.scale;
//    private static final float DIST_BETWEEN = 100F * Settings.scale;
//    private static final float POS_Y = Settings.HEIGHT / 3F;

    public String id;
    public String name;
    public int progress = 0;
    public ArrayList<BoardEffect> effects = new ArrayList<>();

    public AbstractBoard(String ID, String name) {
        this.name = name;
        this.id = ID;
    }

    private static ArrayList<String> ids = new ArrayList<>();

    static {
        ids.add(ChampsArena.ID);
        ids.add(TheLibrary.ID);
        ids.add(TombOfRorrim.ID);
    }

    public static AbstractBoard getRunBoard() {
        //TODO: Date-based system
        return getBoardByID(ids.get(MathUtils.random(0, ids.size() - 1)));
    }

    public void proceed() {
        FishingMod.delvedThisTurn = true;
        progress += 1;
        if (progress > effects.size()) {
            progress = 1;
        }
        effects.get(progress - 1).activate();
    }

    public void reset() {
        progress = 0;
        FishingMod.delvedThisTurn = false;
    }

//    public void render(SpriteBatch sb) {
//        for (int i = 0; i < effects.size(); i++) {
//            effects.get(i).render(sb, i, POS_X, POS_Y + (DIST_BETWEEN * i));
//        }
//        sb.draw(arrow, POS_X, POS_Y + (DIST_BETWEEN * progress));
//    }

    public static AbstractBoard getBoardByID(String ID) {
        if (ID.equals(ChampsArena.ID)) {
            return new ChampsArena();
        } else if (ID.equals(TheLibrary.ID)) {
            return new TheLibrary();
        } else if (ID.equals(TombOfRorrim.ID)) {
            return new TombOfRorrim();
        }
        return new ChampsArena();
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(FontHelper.colorString(name, "p") + " NL ");
        sb.append("#gSpecial #gRule: " + getSpecialRule() + " NL ");
        for (int i = 0; i < effects.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(effects.get(i).description);
            if (i < effects.size() - 1)
                sb.append(" NL ");
        }
        return sb.toString();
    }

    private String getSpecialRule() {
        return "None"; //TODO: Unhardcode
    }
}
