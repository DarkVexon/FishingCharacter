package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class FutureProofing extends AbstractFishingCard {
    public final static String ID = makeID("FutureProofing");
    // intellij stuff attack, enemy, common, 7, 2, , , , 1

    public FutureProofing() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new ScryAction(magicNumber));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.drawPile.getTopCard().upgrade();
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}