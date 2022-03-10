package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.actions.DrawSpecificColorCardAction;
import theFishing.powers.LambdaPower;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class TackleBox extends AbstractFishingCard {
    public final static String ID = makeID("TackleBox");
    // intellij stuff power, self, uncommon, , , , , 1, 1

    public TackleBox() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Tackle Box", AbstractPower.PowerType.BUFF, false, p, magicNumber) {
            @Override
            public void atStartOfTurnPostDraw() {
                flash();
                addToBot(new DrawSpecificColorCardAction(amount, CardColor.COLORLESS));
            }

            @Override
            public void updateDescription() {
                description = "At the start of your turn, draw #b" + amount + (amount == 1 ? " Colorless card." : " Colorless cards.");
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}