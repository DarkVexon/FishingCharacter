package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
import theFishing.effects.ColoredSanctityEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class AquaOrb extends AbstractFishingCard {
    public final static String ID = makeID("AquaOrb");
    // intellij stuff skill, self, , , , 17, 6, , 

    public AquaOrb() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new WeakPower(q, magicNumber, false)));
        blck();
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() <= 1) {
            atb(new VFXAction(new ColoredSanctityEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.SKY.cpy())));
            applyToSelf(new ArtifactPower(p, 1));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}