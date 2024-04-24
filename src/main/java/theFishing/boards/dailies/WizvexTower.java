package theFishing.boards.dailies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import theFishing.FishingMod;
import theFishing.actions.ConjureAction;
import theFishing.boards.AbstractBoard;
import theFishing.cards.BurningStudy;
import theFishing.cards.Cryostasis;
import theFishing.cards.Darkleech;
import theFishing.cards.Thunderbolt;

import java.util.ArrayList;

import static theFishing.util.Wiz.att;

public class WizvexTower extends AbstractBoard {
    private static final float X_DIST = 300F;
    private static final float Y_DIST = 80F;
    private static final float DIST_BETWEEN_CARDS = MathUtils.PI2;
    private static final float TIME_MULT = MathUtils.PI2 * 0.075F;
    private static final float SPELL_SIZE = 0.5F;
    private static final float SPELL_SIZE_MOD = 0.166F;
    private static final float SPELL_TRANSPARENCY = 0.5F;

    public static final String ID = FishingMod.makeID(WizvexTower.class.getSimpleName());

    public WizvexTower() {
        super(ID);
    }

    @Override
    public void effect() {
        att(new ConjureAction(false, false));
    }

    private static final ArrayList<String> spells = new ArrayList<>();

    public ArrayList<CardRenderInfo> spellCards = new ArrayList<>();
    public static float time;

    static {
        spells.add(BurningStudy.ID);
        spells.add(Cryostasis.ID);
        spells.add(Thunderbolt.ID);
        spells.add(Darkleech.ID);
    }

    public void refreshSpells() {
        spellCards.clear();
        for (int i = 0; i < spells.size(); i++) {
            addSpellCard(CardLibrary.getCard(spells.get(i)).makeCopy());
        }
    }

    public void addSpellCard(AbstractCard card) {
        spellCards.add(new CardRenderInfo(card));
        updateTimeOffsets();
    }

    public boolean removeSpellCard(AbstractCard card) {
        int idx = getIndexOfCard(card);
        if (idx != -1) {
            spellCards.remove(getIndexOfCard(card));
            updateTimeOffsets();
            return true;
        }
        return false;
    }


    public void updateTimeOffsets() {
        for (int i = 0; i < spellCards.size(); i++) {
            spellCards.get(i).timeOffset = ((float) i / (float) spellCards.size()) * DIST_BETWEEN_CARDS;
        }
    }

    @Override
    public void atBattleStartPreDraw() {
        refreshSpells();
    }

    public void update() {
        time += Gdx.graphics.getDeltaTime() * TIME_MULT;
        for (CardRenderInfo c : spellCards) {
            c.updatePositions();
            c.card.update();
        }
    }

    public void prePlayerRender(SpriteBatch sb) {
        for (CardRenderInfo c : spellCards) {
            if (c.renderBehind) {
                c.card.render(sb);
            }
        }
    }

    public void postPlayerRender(SpriteBatch sb) {
        for (CardRenderInfo c : spellCards) {
            if (!c.renderBehind) {
                c.card.render(sb);
            }
        }
    }

    public int getIndexOfCard(AbstractCard card) {
        for (int i = 0; i < spellCards.size(); i++) {
            if (spellCards.get(i).card.cardID.equals(card.cardID)) {
                return i;
            }
        }
        return -1;
    }

    public static class CardRenderInfo {
        public boolean renderBehind;
        private float timeOffset;
        public AbstractCard card;

        public CardRenderInfo(AbstractCard card) {
            card.targetTransparency = card.transparency = SPELL_TRANSPARENCY;
            this.card = card;
        }

        public void updatePositions() {
            card.target_x = (float) (X_DIST * Math.cos(time + timeOffset)) + (AbstractDungeon.player.drawX + (AbstractDungeon.player.hb.width / 5));
            card.target_y = (float) (Y_DIST * Math.sin(time + timeOffset)) + (AbstractDungeon.player.drawY + (AbstractDungeon.player.hb.height / 2));
            boolean isBehind = ((time + timeOffset) / MathUtils.PI) % 2 < 1;
            renderBehind = isBehind;
            float adjustedTime1 = (time + timeOffset) % MathUtils.PI;
            float adjustedTime2 = ((time + timeOffset) % (MathUtils.PI / 2));
            if (adjustedTime1 / (MathUtils.PI / 2) >= 1) {
                adjustedTime2 = (MathUtils.PI / 2) - adjustedTime2;
            }
            float sizeMod = ((adjustedTime2 / (MathUtils.PI / 2)) * SPELL_SIZE_MOD);
            card.targetDrawScale = SPELL_SIZE + (sizeMod * (isBehind ? -1 : 1));
        }
    }
}
