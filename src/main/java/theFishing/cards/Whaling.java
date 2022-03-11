package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Whaling extends AbstractFishingCard {
    public final static String ID = makeID("Whaling");
    public final static int GOLD_COST = 15;
    // intellij stuff power, self, uncommon, , , , , , 

    public Whaling() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, 1));
        if (p.gold > 200) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    p.loseGold(GOLD_COST);
                    applyToSelfTop(new StrengthPower(p, 1));
                }
            });
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.player.gold > 200 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}