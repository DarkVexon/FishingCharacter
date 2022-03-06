package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class WakaWaka extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("WakaWaka");
    // intellij stuff attack, enemy, uncommon, 8, 2, , , , 

    public WakaWaka() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        shuffleIn(new PowerPellet(), 2);
        return true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeDamage(2);
        selfRetain = true;
        uDesc();
    }
}