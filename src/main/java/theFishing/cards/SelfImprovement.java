package theFishing.cards;

import basemod.cardmods.RetainMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.ApplyCardModifierAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class SelfImprovement extends AbstractFishingCard {
    public final static String ID = makeID("SelfImprovement");
    // intellij stuff skill, self, common, , , , , , 

    public SelfImprovement() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction("to Upgrade and Retain.", (cards) -> {
            AbstractCard c = cards.get(0);
            att(new ApplyCardModifierAction(c, new RetainMod()));
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    c.upgrade();
                }
            });
        }));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}