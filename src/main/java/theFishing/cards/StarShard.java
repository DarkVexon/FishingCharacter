package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class StarShard extends AbstractFishingCard {
    public final static String ID = makeID("StarShard");
    // intellij stuff attack, enemy, special, 3, 2, 3, 2, , 

    public StarShard() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = 9;
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new DrawCardAction(1));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeDamage(3);
    }
}