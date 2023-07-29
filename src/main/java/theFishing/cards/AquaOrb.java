package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import theFishing.effects.ColoredSanctityEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class AquaOrb extends AbstractFishingCard  {
    public final static String ID = makeID("AquaOrb");
    // intellij stuff skill, self, , , , 17, 6, , 

    public AquaOrb() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 11;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.SKY, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.1F));
        forAllMonstersLiving(q -> applyToEnemy(q, new WeakPower(q, magicNumber, false)));
        blck();
        if (GameActionManager.turn == 1) {
            atb(new VFXAction(new ColoredSanctityEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.SKY.cpy())));
            applyToSelf(new ArtifactPower(p, 1));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = GameActionManager.turn == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}