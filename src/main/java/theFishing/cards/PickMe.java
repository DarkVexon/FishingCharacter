package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import java.util.ArrayList;


import static theFishing.FishingMod.makeID;

public class PickMe extends AbstractFishingCard implements SpawnModificationCard {
    public final static String ID = makeID("PickMe");
    // intellij stuff attack, all, uncommon, 9, 2, 3, 1, , 

    public PickMe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL);
        baseDamage = 9;
        baseBlock = 3;
        isMultiDamage = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void onRewardListCreated(ArrayList<AbstractCard> rewardCards) {
        boolean triggered = false;
        for (AbstractCard q : rewardCards) {
            if (!FoilPatches.isFoil(q)) {
                FoilPatches.makeFoil(q);
                triggered = true;
            }
        }
        if (triggered) {
            superFlash(Color.BLUE);
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(2);
    }
}