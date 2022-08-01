package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Offering;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import theFishing.FishingMod;
import theFishing.powers.LambdaPower;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.StarHelper.isStarCard;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class StarOfTheShow extends AbstractFishingCard {
    public final static String ID = makeID("StarOfTheShow");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public StarOfTheShow() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        applyToSelf(new LambdaPower("Star of the Show", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                if (isStarCard(card)) {
                    flashWithoutSound();
                    atb(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
                    atb(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, amount), amount));
                }
            }

            @Override
            public void updateDescription() {
                description = "Whenever you play a card with a star in its art, gain #b" + amount + " #yVigor.";
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}