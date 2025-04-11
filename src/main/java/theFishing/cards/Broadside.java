package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Broadside extends AbstractFishingCard {
    public final static String ID = makeID(Broadside.class.getSimpleName());
    // intellij stuff attack, enemy, common, 18, 6, , , , 

    public Broadside() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
        cardsToPreview = new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard card = new Miracle();
        topDeck(card);
    }

    public void upp() {
        upgradeBlock(4);
    }
}