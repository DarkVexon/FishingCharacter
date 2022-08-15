package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DarkOrbEvokeAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class EndsOfTheEarth extends AbstractFishingCard {
    public final static String ID = makeID("EndsOfTheEarth");
    // intellij stuff attack, self_and_enemy, rare, 6, 3, 5, 3, , 

    public EndsOfTheEarth() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    @Override
    public void onMoveToDiscard() {
        cost = 1;
        costForTurn = 1;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(1);
    }
}