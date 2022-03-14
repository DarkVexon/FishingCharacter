package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantEyeEffect;

import java.util.ArrayList;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class SearingSight extends AbstractFishingCard {
    public final static String ID = makeID("SearingSight");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public SearingSight() {
        this(0);
    }

    public SearingSight(int upgrades) {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.timesUpgraded = upgrades;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
            if (timesUpgraded >= 2) {
                c.upgrade();
            }
            cardsList.add(c);
        }
        atb(new SelectCardsCenteredAction(cardsList, "Choose a card to add into your hand.", (cards) -> {
            AbstractCard q = cards.get(0);
            if (timesUpgraded >= 3) {
                q.freeToPlayOnce = true;
            }
            att(new MakeTempCardInHandAction(q,true));
        }));
        atb(new VFXAction(new GiantEyeEffect(p.hb.cX, p.hb.cY + 100.0F * Settings.scale, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 0.0F))));
    }

    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();

        if (timesUpgraded == 1) {
            upgradeBaseCost(0);
        } else if (timesUpgraded == 2) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        } else if (timesUpgraded == 3) {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < 3;
    }

    public void upp() {
    }
}