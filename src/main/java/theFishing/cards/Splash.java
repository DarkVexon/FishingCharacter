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
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = upgraded ? 17 : 15;
        int realBaseBlock = upgraded ? 17 : 15;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * 2;
        baseDamage -= modifier;
        baseBlock -= modifier;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.baseBlock = realBaseBlock;
        this.isDamageModified = this.damage != this.baseDamage;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}