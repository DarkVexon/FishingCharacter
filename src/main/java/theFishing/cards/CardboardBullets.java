package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;

import static theFishing.FishingMod.makeID;

public class CardboardBullets extends AbstractFishingCard {
    public final static String ID = makeID("CardboardBullets");
    // intellij stuff attack, enemy, common, , , , , , 

    public CardboardBullets() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.3F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * 2;
        baseDamage -= modifier;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count() * 2;
        baseDamage -= modifier;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void upp() {
        upgradeDamage(3);
    }
}