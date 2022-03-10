package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;

public class PickMe extends AbstractFishingCard implements SpawnModificationCard {
    public final static String ID = makeID("PickMe");
    // intellij stuff attack, all, uncommon, 9, 2, 3, 1, , 

    public PickMe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL);
        baseDamage = 9;
        baseBlock = 3;
        isMultiDamage = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void onRewardListCreated(ArrayList<AbstractCard> rewardCards) {
        for (AbstractCard q : rewardCards) {
            if (q != this) {
                q.upgrade();
            }
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(2);
    }
}