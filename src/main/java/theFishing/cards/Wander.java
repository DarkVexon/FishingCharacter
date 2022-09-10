package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Wander extends AbstractFishingCard {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileToTopOfDeckAction");
    public static final String[] TEXT = uiStrings.TEXT;
    public final static String ID = makeID("Wander");
    // intellij stuff attack, all_enemy, common, 9, 1, , , 1, 1

    public Wander() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new SelectCardsAction(AbstractDungeon.player.drawPile.group, 1, uiStrings.TEXT[0], (cards) -> {
            for (AbstractCard q : cards) {
                AbstractDungeon.player.drawPile.removeCard(q);
                AbstractDungeon.player.drawPile.addToTop(q);
            }
        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}