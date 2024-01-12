package theFishing.boards;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.boards.dailies.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

public abstract class AbstractBoard {

    public String id;
    public String name;

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

    public static final HashMap<String, Class<? extends AbstractBoard>> ids = new LinkedHashMap<>();
    public static final ArrayList<String> idsList = new ArrayList<>();

    static {
        ids.put(ChampsArena.ID, ChampsArena.class);
        idsList.add(ChampsArena.ID);
        ids.put(WizvexTower.ID, WizvexTower.class);
        idsList.add(WizvexTower.ID);
        ids.put(ThortonsBank.ID, ThortonsBank.class);
        idsList.add(ThortonsBank.ID);
        ids.put(Circuitry.ID, Circuitry.class);
        idsList.add(Circuitry.ID);
        ids.put(MegaCrit.ID, MegaCrit.class);
        idsList.add(MegaCrit.ID);
        ids.put(TheCannon.ID, TheCannon.class);
        idsList.add(TheCannon.ID);
        ids.put(LandOfGiants.ID, LandOfGiants.class);
        idsList.add(LandOfGiants.ID);
        ids.put(TheDeep.ID, TheDeep.class);
        idsList.add(TheDeep.ID);
        ids.put(Termina.ID, Termina.class);
        idsList.add(Termina.ID);
        ids.put(WhereItFell.ID, WhereItFell.class);
        idsList.add(WhereItFell.ID);
    }

    private static final String debugOverride = null;

    public static AbstractBoard getRunBoard() {
        switch (FishingMod.delvePreference) {
            case 0:
                Calendar calendar = Calendar.getInstance();
                int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
                ArrayList<String> idsToUse = new ArrayList<>();
                idsToUse.addAll(ids.keySet());
                if (debugOverride != null && CardCrawlGame.playerName.toLowerCase().contains("vex")) {
                    return getBoardByID(debugOverride);
                } else {
                    return AbstractBoard.getBoardByID(idsToUse.get(dayOfYear % idsToUse.size()));
                }
            case 1:
                return AbstractBoard.getBoardByID(idsList.get(MathUtils.random(idsList.size() - 1)));
            default:
                if (FishingMod.delvePreference - 2 < idsList.size()) {
                    return AbstractBoard.getBoardByID(idsList.get(FishingMod.delvePreference - 2));
                } else {
                    return AbstractBoard.getBoardByID(idsList.get(MathUtils.random(idsList.size() - 1)));
                }
        }
    }

    public void proceed() {
        FishingMod.delvedThisTurn = true;
        effect();
    }

    public abstract void effect();

    public void reset() {
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
        String sb = FontHelper.colorString(name, "p") + " NL " + "#gSpecial #gRule: " + getSpecialRule() + " NL " + getEffectsText();
        return sb;
    }

    public String getEffectsText() {
        return "#yKeyword #yEffect: " + getLocString(id).TEXT_DICT.get("F1");
    }

    public String getSpecialRule() {
        return getLocString(id).TEXT_DICT.containsKey("SP") ? getLocString(id).TEXT_DICT.get("SP") : TopPanelBoard.uiStrings.TEXT[4];
    }

    public void onUnusedBlock(int unusedBlock) {

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

    public static void postInitDelveState(AbstractCard card) {
        if (!card.hasTag(FishingMod.DELVES)) {
            card.tags.add(FishingMod.DELVES);
            if (FishingMod.activeBoard != null && CardCrawlGame.isInARun() && FishingMod.activeBoard.id.equalsIgnoreCase(LandOfGiants.ID)) {
                AbstractCard source = CardLibrary.getCard(card.cardID);
                card.cost = source.cost + 1;
                card.costForTurn = card.cost;
            }
        }
    }
}
