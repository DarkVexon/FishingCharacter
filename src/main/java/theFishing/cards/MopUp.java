package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class MopUp extends AbstractFishingCard {
    public final static String ID = makeID("MopUp");
    // intellij stuff attack, enemy, common, 7, 3, , , , 

    public MopUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            if (cards.get(0).type == CardType.STATUS || cards.get(0).type == CardType.CURSE || cards.get(0).color == CardColor.CURSE) {
                att(new DrawCardAction(1));
                att(new GainEnergyAction(1));
            }
            att(new ExhaustSpecificCardAction(cards.get(0), p.hand));
        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}