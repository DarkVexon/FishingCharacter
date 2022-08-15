package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Starfy extends AbstractFishCard {
    public static final String ID = makeID("Starfy");

    public Starfy() {
        super(ID, CardType.ATTACK, CardTarget.ALL);
        baseDamage = 6;
        baseBlock = 6;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new SFXAction("ATTACK_WHIRLWIND"));
        atb(new VFXAction(new WhirlwindEffect(), 0.0F));
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new DrawCardAction(1));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}
