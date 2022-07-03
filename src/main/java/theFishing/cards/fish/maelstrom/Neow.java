package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Neow extends AbstractFishCard {
    public final static String ID = makeID("Neow");
    // intellij stuff skill, enemy, , , , , 50, 10

    public Neow() {
        super(ID, 0, CardType.SKILL, CardTarget.SELF_AND_ENEMY);
        baseMagicNumber = magicNumber = 30;
        exhaust = true;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TalkAction(true, "catch... and release...", 1.0F, 2.0F));
        atb(new LoseHPAction(m, p, magicNumber));
        atb(new DrawCardAction(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(7);
    }
}