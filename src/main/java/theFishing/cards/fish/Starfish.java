package theFishing.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.actions.DamagePlusWallopVFXAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class Starfish extends AbstractFishCard {
    public final static String ID = makeID("Starfish");
    // intellij stuff skill, enemy, special, , , , , 2, 1

    public Starfish() {
        super(ID, CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseDamage = 3;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        atb(new DamagePlusWallopVFXAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }
}