package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.actions.EasyXCostAction;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RatedX extends AbstractFishingCard {
    public final static String ID = makeID("RatedX");
    // intellij stuff attack, enemy, rare, 9, 3, 14, 4, 1, 1

    public RatedX() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 9;
        baseBlock = 14;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect >= 4) {
                att(new GainBlockAction(p, block));
            }
            if (effect >= 2) {
                applyToSelfTop(new StrengthPower(p, magicNumber));
            }
            if (effect >= 1) {
                applyToEnemyTop(m, new WeakPower(m, magicNumber, false));
                applyToEnemyTop(m, new VulnerablePower(m, magicNumber, false));
            }
            dmgTop(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }
}