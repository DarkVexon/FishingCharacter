package theFishing.boards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.TimeHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import theFishing.FishingMod;
import theFishing.boards.dailies.*;
import theFishing.util.Wiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

    private static HashMap<String, Class<? extends AbstractBoard>> ids = new LinkedHashMap<>();

    static {
        ids.put(ChampsArena.ID, ChampsArena.class);
        ids.put(TombOfRorrim.ID, TombOfRorrim.class);
        ids.put(TheDeep.ID, TheDeep.class);
        ids.put(TheFactory.ID, TheFactory.class);
        ids.put(TheStarship.ID, TheStarship.class);
        ids.put(WatchersTemple.ID, WatchersTemple.class);
        ids.put(WhereItFell.ID, WhereItFell.class);
        ids.put(TheLibrary.ID, TheLibrary.class);
    }

    public static AbstractBoard getRunBoard() {
        ArrayList<String> idsToUse = new ArrayList<>();
        idsToUse.addAll(ids.keySet());
        if (CardCrawlGame.playerName.toLowerCase().contains("vex")) {
            return AbstractBoard.getBoardByID(idsToUse.get(MathUtils.random(idsToUse.size() - 1)));
        }
        return AbstractBoard.getBoardByID(idsToUse.get((int) (TimeHelper.daySince1970() % ids.size())));
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
        try {
            return ids.get(ID).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("ERROR! Couldn't make board by ID");
            return new ChampsArena();
        }
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(FontHelper.colorString(name, "p") + " NL ");
        sb.append("#gSpecial #gRule: " + getSpecialRule() + " NL ");
        for (int i = 0; i < effects.size(); i++) {
            sb.append(getEffectDescription(i));
            if (i < effects.size() - 1)
                sb.append(" NL ");
        }
        return sb.toString();
    }

    public String getSpecialRule() {
        return "None"; //TODO: Unhardcode
    }

    public void atBattleStartPreDraw() {

    }

    public String getEffectDescription(int i) {
        StringBuilder sb = new StringBuilder();
        if (progress == i && Wiz.isInCombat()) {
            sb.append("#r");
        }
        sb.append(i + 1);
        sb.append(". ");
        sb.append(effects.get(i).description);
        return sb.toString();
    }
}
