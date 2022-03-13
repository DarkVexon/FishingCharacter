package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.DivinityStance;
import theFishing.cards.treasures.AbstractTreasureCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class LostStaff extends AbstractTreasureCard {
    public final static String ID = makeID("LostStaff");
    // intellij stuff skill, self, , , , , , 

    public LostStaff() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChangeStanceAction(DivinityStance.STANCE_ID));
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}