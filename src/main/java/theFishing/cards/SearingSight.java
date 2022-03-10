package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import java.util.ArrayList;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SearingSight extends AbstractFishingCard {
    public final static String ID = makeID("SearingSight");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public SearingSight() {
        this(0);
    }

    public SearingSight(int upgrades) {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.timesUpgraded = upgrades;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsList = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            cardsList.add(AbstractDungeon.returnTrulyRandomCardInCombat());
        }
        atb(new SelectCardsCenteredAction(cardsList, "to add into your hand.", (cards) -> {
            AbstractCard q = cards.get(0);
            att(new MakeTempCardInHandAction(q, true));
        }));
    }

    public void upgrade() {
        upp();
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}