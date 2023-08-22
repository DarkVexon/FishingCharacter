package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.FutureProofingAction;
import theFishing.effects.ColoredBeamEffect;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class FutureProofing extends AbstractFishingCard {
    public final static String ID = makeID("FutureProofing");
    // intellij stuff attack, enemy, common, 7, 2, , , , 1

    public FutureProofing() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_DEFECT_BEAM"));
        Color c = FoilPatches.isFoil(this) ? Color.BLUE.cpy() : Color.RED.cpy();
        Color c2 = FoilPatches.isFoil(this) ? Color.SKY.cpy() : Color.FIREBRICK.cpy();
        atb(new VFXAction(new ColoredBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal, c, c2), 0.4F));
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new FutureProofingAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
        uDesc();
    }
}