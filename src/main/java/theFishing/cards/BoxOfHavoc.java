package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class BoxOfHavoc extends AbstractFishingCard {
    public final static String ID = makeID("BoxOfHavoc");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public BoxOfHavoc() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = p.hand.size() == 1 ? magicNumber : 1;
        forAllMonstersLiving(q -> {
            applyToEnemy(q, new WeakPower(q, x, false));
            applyToEnemy(q, new VulnerablePower(q, x, false));
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}