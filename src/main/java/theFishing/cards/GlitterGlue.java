package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import theFishing.patch.foil.FoilPatches;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class GlitterGlue extends AbstractFishingCard implements SpawnModificationCard {
    public final static String ID = makeID("GlitterGlue");
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public GlitterGlue() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIFF_2", 0.3F));
        atb(new SFXAction("ATTACK_FAST", 0.2F));
        atb(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(4);
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