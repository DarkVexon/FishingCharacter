package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.AbstractFishingCard;
import theFishing.quest.quests.TheTerribleTwos;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeTheTerribleTwos extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheTerribleTwos");
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public QuestTimeTheTerribleTwos() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        atb(new AcceptQuestAction(new TheTerribleTwos()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}