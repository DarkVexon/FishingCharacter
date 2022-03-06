package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;
import theFishing.actions.FatalRunnableAction;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Harpoon extends AbstractFishingCard {
    public final static String ID = makeID("Harpoon");
    // intellij stuff attack, enemy, uncommon, 18, 5, , , 2, 1

    public Harpoon() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FatalRunnableAction(m, new DamageInfo(p, damage, damageTypeForTurn), () -> {
            FishingMod.nextCombatFish += magicNumber;
        }));
    }

    public void upp() {
        upgradeDamage(5);
        upgradeMagicNumber(1);
    }
}