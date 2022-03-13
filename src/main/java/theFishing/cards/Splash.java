package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Splash extends AbstractFishingCard {
    public final static String ID = makeID("Splash");
    // intellij stuff attack, self_and_enemy, , 15, 2, 15, 2, ,

    public Splash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
        baseBlock = 15;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * magicNumber;
        baseDamage -= modifier;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * magicNumber;
        baseDamage -= modifier;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowersToBlock() {
        int realBaseBlock = this.baseBlock;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * magicNumber;
        baseBlock -= modifier;
        super.applyPowersToBlock();
        baseBlock = realBaseBlock;
        isBlockModified = block != baseBlock;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}