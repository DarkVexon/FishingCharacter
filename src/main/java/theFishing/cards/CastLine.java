package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.shuffleIn;

public class CastLine extends AbstractFishingCard {
    public final static String ID = makeID("CastLine");
    // intellij stuff skill, self, basic, , , , , 2, 1

    public CastLine() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TalkAction(true, "HELO! I'm running on an NL " + System.getProperty("os.name").toLowerCase() + "!", 1.0F, 2.0F));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}