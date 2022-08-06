package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.fish.maelstrom.TheWhale;
import theFishing.quest.quests.TheHarpoon;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class QuestTimeTheHarpoon extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("QuestTimeTheHarpoon");
    // intellij stuff attack, enemy, rare, 24, 6, , , , 

    public QuestTimeTheHarpoon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 20;
        cardsToPreview = new TheWhale();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new VerticalImpactEffect(m.hb.cX, m.hb.cY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new AcceptQuestAction(new TheHarpoon()));
        return false;
    }
}