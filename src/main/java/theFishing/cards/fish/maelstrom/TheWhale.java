package theFishing.cards.fish.maelstrom;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;


import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class TheWhale extends AbstractFishCard {
    public final static String ID = makeID("TheWhale");
    // intellij stuff skill, enemy, , , , , 50, 10

    public TheWhale() {
        super(ID,  CardType.SKILL, CardTarget.SELF_AND_ENEMY);
        baseMagicNumber = magicNumber = 20;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        playSfx();
        atb(new LoseHPAction(m, p, magicNumber));
        atb(new DrawCardAction(p, 1));
    }

    private void playSfx() {
        int roll = MathUtils.random(3);
        if (roll == 0) {
            atb(new SFXAction("VO_NEOW_1A"));
        } else if (roll == 1) {
            atb(new SFXAction("VO_NEOW_1B"));
        } else if (roll == 2) {
            atb(new SFXAction("VO_NEOW_2A"));
        } else {
            atb(new SFXAction("VO_NEOW_2B"));
        }

    }

    public void upp() {
        upgradeMagicNumber(6);
    }
}