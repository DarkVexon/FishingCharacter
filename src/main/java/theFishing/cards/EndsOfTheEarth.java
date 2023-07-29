package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.effects.InversionBeamEffect2;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.vfx;

public class EndsOfTheEarth extends AbstractFishingCard {
    public final static String ID = makeID("EndsOfTheEarth");
    // intellij stuff attack, self_and_enemy, rare, 6, 3, 5, 3, , 

    public EndsOfTheEarth() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 5;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        vfx(new InversionBeamEffect2(MathUtils.random(m.hb.x + (m.hb.width / 3F), m.hb.x + ((m.hb.width / 3F) * 2F))));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void onMoveToDiscard() {
        cost = 1;
        costForTurn = 1;
        isCostModified = false;
        isCostModifiedForTurn = false;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(1);
    }
}