package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class MaybeLater extends AbstractFishingCard {
    public final static String ID = makeID("MaybeLater");
    // intellij stuff attack, self_and_enemy, common, 7, 2, 4, 2, , 

    public MaybeLater() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!p.hand.isEmpty()) {
                    att(new GainBlockAction(p, block));
                }
                att(new PutOnDeckAction(p, p, 1, false));
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}