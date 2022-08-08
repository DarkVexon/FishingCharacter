package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class HelloThere extends AbstractBoxTopper {
    public final static String ID = makeID("HelloThere");
    // intellij stuff skill, all_enemy, , , , , 1, 1

    public HelloThere() {
        super(ID, 0, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 1;
        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster q = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                applyToEnemyTop(q, new VulnerablePower(q, magicNumber, false));
                applyToEnemyTop(q, new WeakPower(q, magicNumber, false));
            }
        });
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}