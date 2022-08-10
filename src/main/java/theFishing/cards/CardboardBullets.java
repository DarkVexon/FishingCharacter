package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class CardboardBullets extends AbstractFishingCard {
    public final static String ID = makeID("CardboardBullets");
    // intellij stuff attack, enemy, common, , , , , , 

    public CardboardBullets() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 0;
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
        if (upgraded) {
            baseDamage += 3;
        }
        super.applyPowers();
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = (upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION);
        initializeDescription();
    }

    public void upp() {
        uDesc();
    }
}