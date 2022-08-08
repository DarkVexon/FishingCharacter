package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Jellyfish extends AbstractFishCard {
    public final static String ID = makeID("Jellyfish");
    // intellij stuff skill, enemy, , , , , 4, 2

    public Jellyfish() {
        super(ID, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new IncreaseMaxOrbAction(1));
        for (int i = 0; i < magicNumber; i++)
            atb(new ChannelAction(new Lightning()));
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}