package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class Starfish extends AbstractFishCard {
    public final static String ID = makeID("Starfish");
    // intellij stuff skill, enemy, special, , , , , 2, 1

    public Starfish() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }
}