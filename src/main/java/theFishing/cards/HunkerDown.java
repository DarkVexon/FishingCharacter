package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class HunkerDown extends AbstractFishingCard {
    public final static String ID = makeID("HunkerDown");
    // intellij stuff skill, self, common, , , , , , 

    public HunkerDown() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 < 3) {
                    att(new GainEnergyAction(1));
                }
            }
        });
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < 3 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeBlock(2);
    }
}