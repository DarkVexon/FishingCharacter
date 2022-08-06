package theFishing.cards;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theFishing.patch.foil.FoilPatches;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.applyToSelfTop;

public class Collector extends AbstractFishingCard {
    public final static String ID = makeID("Collector");
    // intellij stuff power, self, uncommon, , , , , , 

    public Collector() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Collector", AbstractPower.PowerType.BUFF, false, p, 1) {
            boolean activated = false;

            @Override
            public void atStartOfTurn() {
                activated = false;
            }

            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                boolean triggered = false;
                if (card.rarity == CardRarity.RARE && !activated) {
                    flash();
                    applyToSelfTop(new VigorPower(owner, amount * 4));
                    triggered = true;
                }
                if (FoilPatches.isFoil(card) && !activated) {
                    flash();
                    applyToSelfTop(new StrengthPower(owner, amount));
                    triggered = true;
                }
                if (triggered) {
                    activated = true;
                }
            }

            @Override
            public void updateDescription() {
                description = "If the first card you play each turn is: NL Rare: Gain #b" + amount * 4 + " #yVigor. NL Foil: Gain #b" + amount + " #yStrength.";
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}