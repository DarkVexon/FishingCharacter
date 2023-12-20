package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Concentrated extends AbstractFishingCard {
    public final static String ID = makeID("Concentrated");
    // intellij stuff power, self, rare, , , , , 2, -1

    public Concentrated() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                att(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
                this.isDone = true;
            }
        });
        applyToSelf(new BerserkPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}