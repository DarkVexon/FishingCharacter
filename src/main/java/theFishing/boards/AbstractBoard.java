package theFishing.boards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;
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

    public AbstractBoard(String ID) {
        this.id = ID;
        this.name = getLocString(ID).TEXT[0];
    }

    private static final HashMap<String, Class<? extends AbstractBoard>> ids = new LinkedHashMap<>();
    private static final HashMap<String, Class<? extends AbstractBoard>> complexIds = new LinkedHashMap<>();

    //TODO: Make this better. automatically add classes with autoadd and split by type as an enum stored in each class

    static {
        ids.put(TheCannon.ID, TheCannon.class);
        ids.put(Circuitry.ID, Circuitry.class);
        ids.put(Termina.ID, Termina.class);
        ids.put(ThortonsBank.ID, ThortonsBank.class);
        ids.put(TheStarship.ID, TheStarship.class);
        ids.put(MegaCrit.ID, MegaCrit.class);
        ids.put(TombOfRorrim.ID, TombOfRorrim.class);
        ids.put(ChampsArena.ID, ChampsArena.class);
        ids.put(XMansion.ID, XMansion.class);
        ids.put(TowerOfSkies.ID, TowerOfSkies.class);


        complexIds.put(KongJungle.ID, KongJungle.class);
        complexIds.put(TheDeep.ID, TheDeep.class);
        complexIds.put(TheFactory.ID, TheFactory.class);
        complexIds.put(WatchersTemple.ID, WatchersTemple.class);
        complexIds.put(WhereItFell.ID, WhereItFell.class);
    }

    private static final String debugOverride = null;

    public static AbstractBoard getRunBoard() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        ArrayList<String> idsToUse = new ArrayList<>();
        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.MONDAY) {
            idsToUse.addAll(complexIds.keySet());
        } else {
            idsToUse.addAll(ids.keySet());
        }
        if (debugOverride != null && CardCrawlGame.playerName.toLowerCase().contains("vex")) {
            return getBoardByID(debugOverride);
        } else {
            return AbstractBoard.getBoardByID(idsToUse.get(dayOfYear % idsToUse.size()));
        }
    }

    public void proceed() {
        FishingMod.delvedThisTurn = true;
        progress += 1;
        if (progress > effects.size()) {
            progress = 1;
        }
        effects.get(progress - 1).run();
    }

    public void reset() {
        progress = 0;
        FishingMod.delvedThisTurn = false;
    }

    public static AbstractBoard getBoardByID(String ID) {
        try {
            if (ids.containsKey(ID)) return ids.get(ID).newInstance();
            else if (complexIds.containsKey(ID)) return complexIds.get(ID).newInstance();
            else {
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
        int value = progress % effects.size();
        if ((value == 1 || (value == 2 && no2) || (value == 3 && no3)) && Wiz.isInCombat()) {
            sb.append("#r");
        }
        sb.append("1");
        if (no2) {
            sb.append(" & 2");
        }
        if (no3) {
            sb.append(" & 3");
        }
        sb.append(": ");
        sb.append(getLocString(id).TEXT_DICT.get("F1"));
        if (!no2) {
            sb.append(" NL ");
            if (value == 2) {
                sb.append("#r");
            }
            sb.append("2: ");
            sb.append(getLocString(id).TEXT_DICT.get("F2"));
        }
        if (!no3) {
            sb.append(" NL ");
            if (value == 3) {
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

    public String onSave() {
        return "";
    }

    public void onLoad(String s) {

    }
}
