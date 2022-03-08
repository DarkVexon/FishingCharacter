package theFishing.cards.quest;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeTheSlimyPath extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheSlimyPath");
    // intellij stuff power, self, rare, , , , , , 

    public QuestTimeTheSlimyPath() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        uDesc();
    }
}