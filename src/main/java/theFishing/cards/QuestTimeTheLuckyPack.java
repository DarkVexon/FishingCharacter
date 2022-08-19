package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.quest.quests.TheLuckyPack;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class QuestTimeTheLuckyPack extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheLuckyPack");
    // intellij stuff attack, all_enemy, uncommon, 7, 3, , , , 

    public QuestTimeTheLuckyPack() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, FIRE);
        atb(new AcceptQuestAction(new TheLuckyPack()));
    }

    public void upp() {
        upgradeDamage(4);
    }
}