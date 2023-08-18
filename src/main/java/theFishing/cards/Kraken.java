package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theFishing.FishingMod.makeID;

public class Kraken extends AbstractFishingCard {
    public final static String ID = makeID("Kraken");
    // intellij stuff attack, enemy, special, 10, , , , , 

    public Kraken() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY, Color.YELLOW.cpy())));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}