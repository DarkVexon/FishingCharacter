package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class FeedingFrenzy extends AbstractFishingCard {
    public final static String ID = makeID(FeedingFrenzy.class.getSimpleName());

    public FeedingFrenzy() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : p.drawPile.group) {
            if (c instanceof AbstractFishCard) {
                addToBot(new ExhaustSpecificCardAction(c, p.drawPile));
                count++;
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c instanceof AbstractFishCard) {
                addToBot(new ExhaustSpecificCardAction(c, p.hand));
                count++;
            }
        }
        for (int i = 0; i < count; i++)
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(2);
    }
}