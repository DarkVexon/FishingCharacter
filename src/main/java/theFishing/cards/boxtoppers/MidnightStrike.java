package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class MidnightStrike extends AbstractBoxTopper {
    public final static String ID = makeID("MidnightStrike");
    // intellij stuff attack, enemy, 80, , , , , 

    public MidnightStrike() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 80;
        tags.add(CardTags.STRIKE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("BELL"));
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() > 11) {
            this.cantUseMessage = "I've already played 12 or more cards this combat!";
            return false;
        } else {
            return super.canUse(p, m);
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}