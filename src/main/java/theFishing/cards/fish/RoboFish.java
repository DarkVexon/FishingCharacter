package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RoboFish extends AbstractFishCard {
    public final static String ID = makeID(RoboFish.class.getSimpleName());
    // intellij stuff atack, enemy, special, 5, 4, , , ,

    public RoboFish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ArtifactPower(p, magicNumber));
    }
}