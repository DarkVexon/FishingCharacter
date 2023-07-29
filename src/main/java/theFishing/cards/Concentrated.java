package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import theFishing.patch.UnnateCoffeePatch;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Concentrated extends AbstractFishingCard {
    public final static String ID = makeID("Concentrated");
    // intellij stuff power, self, rare, , , , , 2, -1

    public Concentrated() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        UnnateCoffeePatch.UnnateField.unnate.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 1));
    }

    public void upp() {
        UnnateCoffeePatch.UnnateField.unnate.set(this, false);
        uDesc();
    }

    @Override
    public void triggerOnExhaust() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.exhaustPile.removeCard(Concentrated.this);
                att(new NewQueueCardAction(Concentrated.this, true, false, true));
            }
        });
    }
}