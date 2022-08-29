package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class BeastSlayer extends AbstractBoxTopper {
    public final static String ID = makeID("BeastSlayer");
    // intellij stuff attack, enemy, 13, 3, , , , 

    public BeastSlayer() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 12;
        baseSecondDamage = secondDamage = 24;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
            atb(new DamageAction(m, new DamageInfo(p, secondDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        } else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(6);
    }
}