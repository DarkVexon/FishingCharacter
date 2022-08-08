package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Skull extends AbstractBoxTopper {
    public final static String ID = makeID("Skull");
    // intellij stuff skill, self, , , 1, 2, , 

    public Skull() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseBlock = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void triggerOnExhaust() {
        atb(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 15));
    }

    public void upp() {
        upgradeBlock(2);
    }
}