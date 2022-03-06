package theFishing.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cardmods.ConsumeMod;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Schooled extends AbstractFishingCard {
    public final static String ID = makeID("Schooled");
    // intellij stuff attack, enemy, rare, 44, 8, , , , 

    public Schooled() {
        super(ID, 6, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 44;
        CardModifierManager.addModifier(this, new ConsumeMod());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(8);
    }
}