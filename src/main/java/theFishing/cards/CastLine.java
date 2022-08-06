package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.shuffleIn;
import static theFishing.util.Wiz.topDeck;

public class CastLine extends AbstractFishingCard {
    public final static String ID = makeID("CastLine");
    // intellij stuff skill, self, basic, , , , , 2, 1

    public CastLine() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}