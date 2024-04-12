package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class PlantFlag extends AbstractFishingCard {
    public final static String ID = makeID("PlantFlag");
    // intellij stuff skill, self, common, , , , , , 

    public PlantFlag() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        AbstractBoard.postInitDelveState(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new WeakPower(q, magicNumber, false)));
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}