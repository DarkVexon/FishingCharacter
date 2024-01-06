package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;

import static theFishing.FishingMod.DELVES;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ProTank extends AbstractFishingCard {
    public final static String ID = makeID("ProTank");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public ProTank() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 7;
        baseBlock = 7;
        AbstractBoard.postInitDelveState(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new EnterTheDungeonAction());
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}