package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class CardboardBullets extends AbstractFishingCard {
    public final static String ID = makeID("CardboardBullets");
    // intellij stuff attack, enemy, common, , , , , , 

    public CardboardBullets() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    private int getEnergyAmount(AbstractCard card) {
        int cost = card.costForTurn;
        if (card.cost == -1) cost = 4;
        if (card.cost == -2) cost = 0;
        if (card.freeToPlayOnce) cost = 0;
        return cost;
    }

    public void applyPowers() {
        baseDamage = AbstractDungeon.player.hand.group.stream().mapToInt(this::getEnergyAmount).sum();
        super.applyPowers();
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }


    public void upp() {
        selfRetain = true;
        uDesc();
    }
}