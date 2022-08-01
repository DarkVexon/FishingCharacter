package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class TheFinalCard extends AbstractFishingCard {
    public final static String ID = makeID("TheFinalCard");
    // intellij stuff attack, all_enemy, rare, 32, 8, , , , 

    public TheFinalCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 32;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() == 1) {
            allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = adp().hand.size() == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(8);
        uDesc();
    }
}