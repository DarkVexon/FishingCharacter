package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Fishbones extends AbstractFishingCard {
    public final static String ID = makeID("Fishbones");
    // intellij stuff attack, enemy, rare, 12, , , , 2, 1

    public Fishbones() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 16;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * AbstractDungeon.player.exhaustPile.size();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * AbstractDungeon.player.exhaustPile.size();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }


    public void upp() {
        upgradeDamage(6);
    }
}