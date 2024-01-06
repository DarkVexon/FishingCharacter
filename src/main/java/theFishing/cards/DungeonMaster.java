package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;
import theFishing.powers.DungeonMasterPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class DungeonMaster extends AbstractFishingCard {
    public final static String ID = makeID("DungeonMaster");
    // intellij stuff power, self, rare, , , , , 25, 7

    public DungeonMaster() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        AbstractBoard.postInitDelveState(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnterTheDungeonAction());
        applyToSelf(new DungeonMasterPower(magicNumber));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}