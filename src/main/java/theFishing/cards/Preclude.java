package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class Preclude extends AbstractFishingCard {
    public final static String ID = makeID("Preclude");
    // intellij stuff attack, enemy, uncommon, 15, 5, , , , 

    public Preclude() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, true), Settings.FAST_MODE ? 0.0F : 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new DiscardAction(p, p, 1, false));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractMonster q : Wiz.getEnemies()) {
                    if (q.hasPower(TimeWarpPower.POWER_ID)) {
                        q.getPower(TimeWarpPower.POWER_ID).amount = 0;
                        q.getPower(TimeWarpPower.POWER_ID).flash();
                        if (q instanceof TimeEater && AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(card -> card.cardID.equals(Preclude.ID)).count() == 1) {
                            att(new TalkAction(q, cardStrings.EXTENDED_DESCRIPTION[1]));
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    public void upp() {
        upgradeDamage(5);
    }
}