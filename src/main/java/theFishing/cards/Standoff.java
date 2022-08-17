package theFishing.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Standoff extends AbstractFishingCard {
    public final static String ID = makeID("Standoff");
    // intellij stuff skill, self, uncommon, , , 6, 2, , 

    public Standoff() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (getEnemies().size() == 1) {
            applyToSelf(new NextTurnBlockPower(p, block));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Wiz.getEnemies().size() == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
    }
}