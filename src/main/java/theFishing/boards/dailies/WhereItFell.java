package theFishing.boards.dailies;

import basemod.BaseMod;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.StarShard;

import java.util.ArrayList;
import java.util.HashMap;

import static theFishing.util.Wiz.makeInHandTop;

public class WhereItFell extends AbstractBoard {
    public static final String ID = FishingMod.makeID(WhereItFell.class.getSimpleName());

    public WhereItFell() {
        super(ID);
    }

    @Override
    public void atRunStart() {
        AbstractDungeon.player.relics.remove(0);
        ArrayList<String> result = colorlessRelics();
        RelicLibrary.getRelic(result.get(MathUtils.random(result.size() - 1))).instantObtain();
    }

    private ArrayList<String> colorlessRelics() {
        ArrayList<String> relicList = new ArrayList<>();
        HashMap result = BaseMod.getAllCustomRelics();
        for (AbstractRelic r : RelicLibrary.commonList) {
            if (!RelicLibrary.redList.contains(r) && !RelicLibrary.greenList.contains(r) && !RelicLibrary.blueList.contains(r) && !RelicLibrary.whiteList.contains(r)) {
                if (!result.containsValue(r)) {
                    relicList.add(r.relicId);
                }
            }
        }
        return relicList;
    }

    @Override
    public void effect() {
        makeInHandTop(new StarShard());
    }
}
