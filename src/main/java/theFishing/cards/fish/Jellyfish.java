package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Jellyfish extends AbstractFishCard {
    public final static String ID = makeID("Jellyfish");
    // intellij stuff skill, enemy, , , , , 4, 2

    public Jellyfish() {
        super(ID, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        atb(new IncreaseMaxOrbAction(magicNumber));
        for (int i = 0; i < magicNumber; i++)
            atb(new ChannelAction(new Lightning()));
    }
}