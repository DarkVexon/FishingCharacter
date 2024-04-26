package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class ExtraTentacle extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("ExtraTentacle");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , , 

    public ExtraTentacle() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        CardGroup possCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard q : p.drawPile.group) {
            possCards.addToRandomSpot(q);
        }
        atb(new SelectCardsAction(possCards.group, 1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> cards.forEach(q -> att(new ExhaustSpecificCardAction(q, p.drawPile, false)))));
    }

    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade()) {
                upgradableCards.add(c);
            }
        }

        if (!upgradableCards.isEmpty()) {
            AbstractCard tar = Wiz.getRandomItem(upgradableCards);
            if (tar != null) {
                tar.upgrade();
                upgradableCards.remove(tar);
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(tar.makeStatEquivalentCopy()));
                AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            }
        }
    }
}