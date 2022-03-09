package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class MagicRainbowStarBarrage extends AbstractFishingCard {
    public final static String ID = makeID("MagicRainbowStarBarrage");
    // intellij stuff attack, enemy, uncommon, 2, 1, , , , 

    public MagicRainbowStarBarrage() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 6; i++) {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void upp() {
        upgradeDamage(1);
    }
}