package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.PerfectPullFollowUpAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class PerfectPull extends AbstractFishingCard {
    public final static String ID = makeID("PerfectPull");
    // intellij stuff attack, all_enemy, common, 5, 2, , , , 

    public PerfectPull() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new DrawCardAction(1, new PerfectPullFollowUpAction(this, m)));
    }

    public void upp() {
        upgradeDamage(3);
    }
}