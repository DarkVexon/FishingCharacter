package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.adp;

public class ThatsAllFolks extends AbstractFishingCard {
    public final static String ID = makeID("ThatsAllFolks");
    // intellij stuff attack, enemy, common, 8, 2, , , , 

    public ThatsAllFolks() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        if (p.hand.size() == 1) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = adp().hand.size() == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(3);
    }
}