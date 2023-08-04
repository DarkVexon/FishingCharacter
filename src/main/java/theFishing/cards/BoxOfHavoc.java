package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import theFishing.actions.DrawUpToEnergyAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class BoxOfHavoc extends AbstractFishingCard {
    public final static String ID = makeID("BoxOfHavoc");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public BoxOfHavoc() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawUpToEnergyAction(magicNumber));
        if (isSolo()) {
            atb(new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
            atb(new GainEnergyAction(magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isSolo() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}