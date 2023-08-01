package theFishing.boards;

import theFishing.FishingMod;

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

//    public void render(SpriteBatch sb) {
//        for (int i = 0; i < effects.size(); i++) {
//            effects.get(i).render(sb, i, POS_X, POS_Y + (DIST_BETWEEN * i));
//        }
//        sb.draw(arrow, POS_X, POS_Y + (DIST_BETWEEN * progress));
//    }

    public static AbstractBoard getBoardByID(String ID) {
        switch (ID) {
            case ChampsArena.ID:
                return new ChampsArena();
            default:
                return new ChampsArena();
        }
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name + " NL ");
        for (int i = 0; i < effects.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(effects.get(i).description);
            if (i < effects.size() - 1)
                sb.append(" NL ");
        }
        return sb.toString();
    }
}
