package theFishing.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Lie extends AbstractFishingCard {
    public final static String ID = makeID("Lie");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 2

    public Lie() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;
        MultiCardPreview.add(this, new Cheat(), new Steal());
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("INTIMIDATE"));
        atb(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));

        forAllMonstersLiving(q -> {
            applyToEnemy(q, new StrengthPower(q, -magicNumber));
            if (!q.hasPower(ArtifactPower.POWER_ID)) {
                applyToEnemy(q, new GainStrengthPower(q, magicNumber));
            }
        });

        makeInHand(new Cheat());
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}