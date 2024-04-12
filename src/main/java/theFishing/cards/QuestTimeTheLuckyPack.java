package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.quests.TheLuckyPack;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class QuestTimeTheLuckyPack extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheLuckyPack");
    // intellij stuff attack, enemy, uncommon, 9, 4, , , , 

    public QuestTimeTheLuckyPack() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new AcceptQuestAction(new TheLuckyPack()));
    }

    public void upp() {
        upgradeDamage(4);
    }
}