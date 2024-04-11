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
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Miracle();
        FoilPatches.makeFoil(cardsToPreview);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
        }
        AbstractCard card = new Miracle();
        FoilPatches.makeFoil(card);
        topDeck(card);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}