package theFishing.boards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.boards.dailies.*;
import theFishing.util.Wiz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class AbstractBoard {

    public String id;
    public String name;
    public int progress = 0;
    public ArrayList<Runnable> effects = new ArrayList<>();

    private static final HashMap<String, UIStrings> locMap = new HashMap<>();

    public UIStrings getLocString(String ID) {
        if (locMap.containsKey(ID)) {
            return locMap.get(ID);
        } else {
            UIStrings toFind = CardCrawlGame.languagePack.getUIString(ID);
            if (toFind != null) {
                locMap.put(ID, toFind);
                return toFind;
            } else {
                return null;
            }
        }
    }

    public boolean shouldBeActive() {
        return AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING);
    }

    public AbstractBoard(String ID) {
        this.id = ID;
        this.name = getLocString(ID).TEXT[0];
    }

    private static final HashMap<String, Class<? extends AbstractBoard>> ids = new LinkedHashMap<>();

    static {
        ids.put(ChampsArena.ID, ChampsArena.class);
        ids.put(WizvexTower.ID, WizvexTower.class);
        ids.put(ThortonsBank.ID, ThortonsBank.class);
        ids.put(MegaCrit.ID, MegaCrit.class);
        ids.put(Circuitry.ID, Circuitry.class);
        ids.put(TheCannon.ID, TheCannon.class);
        ids.put(KongJungle.ID, KongJungle.class);
        ids.put(TheDeep.ID, TheDeep.class);
        ids.put(Termina.ID, Termina.class);
        ids.put(WhereItFell.ID, WhereItFell.class);
    }

    private static final String debugOverride = null;

    public static AbstractBoard getRunBoard() {
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        ArrayList<String> idsToUse = new ArrayList<>();
        idsToUse.addAll(ids.keySet());
        if (debugOverride != null && CardCrawlGame.playerName.toLowerCase().contains("vex")) {
            return getBoardByID(debugOverride);
        } else {
            return AbstractBoard.getBoardByID(idsToUse.get(dayOfYear % idsToUse.size()));
        }
    }

    public void proceed() {
        FishingMod.delvedThisTurn = true;
        effects.get(progress).run();
        progress += 1;
        if (progress == 3) {
            progress = 0;
        }
    }

    public void reset() {
        progress = 0;
        FishingMod.delvedThisTurn = false;
    }

    public static AbstractBoard getBoardByID(String ID) {
        try {
            if (ids.containsKey(ID)) {
                return ids.get(ID).newInstance();
            } else {
                System.out.println("ERROR! Couldn't find board by ID");
                return new ChampsArena();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("ERROR! Couldn't make board by ID");
            return new ChampsArena();
        }
    }

    public String getDescription() {
        String sb = FontHelper.colorString(name, "p") + " NL " +
                "#gSpecial #gRule: " + getSpecialRule() + " NL " +
                getEffectsText();
        return sb;
    }

    private String getEffectsText() {
        StringBuilder sb = new StringBuilder();
        boolean no2 = !getLocString(id).TEXT_DICT.containsKey("F2");
        boolean no3 = !getLocString(id).TEXT_DICT.containsKey("F3");
        int value = (progress + 1);
        if (value == 1 && Wiz.isInCombat()) {
            sb.append("#r");
        }
        sb.append("1");
        if (no2) {
            sb.append(" & ");
            if (value == 2 && Wiz.isInCombat()) {
                sb.append("#r");
            }
            sb.append("2");
        }
        if (no3) {
            sb.append(" & ");
            if (value == 3 && Wiz.isInCombat()) {
                sb.append("#r");
            }
            sb.append("3");
        }
        sb.append(": ");
        sb.append(getLocString(id).TEXT_DICT.get("F1"));
        if (!no2) {
            sb.append(" NL ");
            if (value == 2 && Wiz.isInCombat()) {
                sb.append("#r");
            }
            sb.append("2: ");
            sb.append(getLocString(id).TEXT_DICT.get("F2"));
        }
        if (!no3) {
            sb.append(" NL ");
            if (value == 3 && Wiz.isInCombat()) {
                sb.append("#r");
            }
            sb.append("3: ");
            sb.append(getLocString(id).TEXT_DICT.get("F3"));
        }
        return sb.toString();
    }

    public String getSpecialRule() {
        return getLocString(id).TEXT_DICT.containsKey("SP") ? getLocString(id).TEXT_DICT.get("SP") : TopPanelBoard.uiStrings.TEXT[4];
    }

    public void atBattleStartPreDraw() {

    }

    public void atRunStart() {

    }

    public void onObtainCard(AbstractCard card) {

    }

    public void atEndOfTurn() {

    }

    public void render(SpriteBatch sb) {

    }

    public void update() {

    }

    public void postPlayerRender(SpriteBatch sb) {

    }

    public void prePlayerRender(SpriteBatch sb) {

    }
}
