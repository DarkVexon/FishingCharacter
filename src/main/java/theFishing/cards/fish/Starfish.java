package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;

public class Starfish extends AbstractFishCard {
    public final static String ID = makeID("Starfish");
    // intellij stuff skill, self, , , , , , 

    public Starfish() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        selfRetain = true;
        uDesc();
    }
}