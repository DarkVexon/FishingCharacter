package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class Shark extends AbstractFishCard {
    public final static String ID = makeID("Shark");
    // intellij stuff skill, enemy, special, , , , , 2, 1

    public Shark() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }
}