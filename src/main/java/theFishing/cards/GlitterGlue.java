package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.patch.foil.FoilPatches;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class GlitterGlue extends AbstractFishingCard implements SpawnModificationCard {
    public final static String ID = makeID("GlitterGlue");
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public GlitterGlue() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
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

}