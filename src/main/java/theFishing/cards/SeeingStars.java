package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import theFishing.actions.DamagePlusWallopVFXAction;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.StarHelper.isStarCard;
import static theFishing.util.Wiz.*;

public class SeeingStars extends AbstractFishingCard {
    public final static String ID = makeID("SeeingStars");
    // intellij stuff attack, enemy, common, 5, 1, , , , 

    public SeeingStars() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 1;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new DamagePlusWallopVFXAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        applyToSelf(new LambdaPower("Starlit Shield", AbstractPower.PowerType.BUFF, true, p, 1) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (isStarCard(card)) {
                    flash();
                    atb(new GainBlockAction(owner, amount));
                }
            }

            @Override
            public void atEndOfTurn(boolean isPlayer) {
                atb(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a card with a star in its art this turn, gain #b" + amount + " #yBlock.";
            }
        });
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}