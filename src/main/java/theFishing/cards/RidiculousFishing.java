package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class RidiculousFishing extends AbstractFishingCard {
    public final static String ID = makeID("RidiculousFishing");
    // intellij stuff power, self, rare, , , , , 4, 1

    public RidiculousFishing() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Ridiculous Fishing", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            public void onAfterCardPlayed(AbstractCard card) {
                if (card.color == CardColor.COLORLESS) {
                    flash();
                    addToBot(new DamageRandomEnemyAction(new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a Colorless card, deal #b" + amount + " damage to a random enemy.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}