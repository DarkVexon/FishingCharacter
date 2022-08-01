package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;
import theFishing.util.StarHelper;

import javax.swing.text.AbstractDocument;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SolarPowered extends AbstractFishingCard {
    public final static String ID = makeID("SolarPowered");
    // intellij stuff skill, self, common, , , 6, 2, 1, 1

    public SolarPowered() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard q = AbstractDungeon.player.drawPile.getTopCard();
                AbstractDungeon.player.drawPile.moveToDiscardPile(q);
                int x = StarHelper.getStarNumber(q);
                applyToSelfTop(new LoseStrengthPower(p, x));
                applyToSelfTop(new StrengthPower(p, x));
            }
        });
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}