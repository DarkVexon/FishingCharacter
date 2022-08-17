package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class DoubleUp extends AbstractFishingCard {
    public final static String ID = makeID("DoubleUp");
    // intellij stuff attack, enemy, common, 4, 2, , , 2, 

    public DoubleUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (p.currentBlock > 0) {
            atb(new DrawCardAction(magicNumber));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.player.currentBlock > 0 ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(2);
    }
}