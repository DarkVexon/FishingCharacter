package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DeckedOut extends AbstractFishingCard {
    public final static String ID = makeID("DeckedOut");
    // intellij stuff skill, self, uncommon, , , 3, 1, 3, 1

    public DeckedOut() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 3;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        if (CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck()) {
            atb(new AnimateJumpAction(p));
            blck();
        }
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}