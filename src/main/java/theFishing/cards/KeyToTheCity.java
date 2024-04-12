package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;
import theFishing.actions.AbandonQuestAction;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.AbstractQuest;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class KeyToTheCity extends AbstractFishingCard {
    public final static String ID = makeID("KeyToTheCity");
    // intellij stuff attack, all_enemy, rare, 10, 4, , , , 

    public KeyToTheCity() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 11;
        AbstractBoard.postInitDelveState(this);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!QuestHelper.quests.isEmpty()) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    QuestHelper.playCompleteQuestSfx();
                    for (int i = QuestHelper.quests.size() - 1; i >= 0; i--) {
                        AbstractQuest next = QuestHelper.quests.get(i);
                        att(new AbandonQuestAction(next));
                        next.grantRewardTop();
                    }
                }
            });
        }
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, true), Settings.FAST_MODE ? 0.0F : 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new EnterTheDungeonAction());
        if (upgraded) {
            atb(new EnterTheDungeonAction());
        }
        applyToSelf(new EquilibriumPower(p, 1));
    }

    public void upp() {
        upgradeDamage(1);
        uDesc();
    }
}