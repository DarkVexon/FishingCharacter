package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.shuffleIn;

public class SeaMonster extends AbstractFishCard {
    public final static String ID = makeID("SeaMonster");
    // intellij stuff skill, none, special, , , , , , 

    public SeaMonster() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_PIERCING_WAIL"));
        for (int i = 0; i < magicNumber; i++) {
            shuffleIn(AbstractFishCard.returnRandomFish());
        }
    }
}