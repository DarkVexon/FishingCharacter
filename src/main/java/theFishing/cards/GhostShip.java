package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.att;
import static theFishing.util.Wiz.vfx;

public class GhostShip extends AbstractFishingCard {
    public final static String ID = makeID(GhostShip.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 6, 3, , , 6, 3

    public GhostShip() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new FireballEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.5F);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void triggerOnExhaust() {
        att(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                GhostShip.this.applyPowers();
            }
        });
        att(new ExhaustToHandAction(this));
        att(new ModifyDamageAction(this.uuid, magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}