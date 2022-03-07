package theFishing.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cardmods.ConsumeMod;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class BloodInTheWater extends AbstractFishingCard {
    public final static String ID = makeID("BloodInTheWater");
    // intellij stuff skill, self, uncommon, , , 12, , 2, 2

    public BloodInTheWater() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        CardModifierManager.addModifier(this, new ConsumeMod());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = (int) Math.min(p.hand.group.stream().filter(c -> c instanceof AbstractFishCard).count(), (this.freeToPlayOnce ? 0 : costForTurn));
        applyToSelf(new StrengthPower(p, 2));
        for (int i = 0; i < x; i++) {
            applyToSelf(new StrengthPower(p, 1));
        }
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}