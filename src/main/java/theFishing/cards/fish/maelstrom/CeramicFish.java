package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class CeramicFish extends AbstractFishCard {
    public static final String ID = makeID("CeramicFish");

    public CeramicFish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseBlock = 5;
    }

    @Override
    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
}
