package theFishing.boards.dailies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.FishingMod;
import theFishing.actions.RepeatCardAction;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class TheFactory extends AbstractBoard {
    public static final String ID = FishingMod.makeID(TheFactory.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;
    private AbstractCard thisCombatCard;

    public TheFactory() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> att(new GainBlockAction(AbstractDungeon.player, 4))));
        effects.add(new BoardEffect(TEXT[2], () -> att(new GainBlockAction(AbstractDungeon.player, 4))));
        effects.add(new BoardEffect(TEXT[3], () -> att(new RepeatCardAction(thisCombatCard.makeStatEquivalentCopy()))));
    }

    @Override
    public void reset() {
        super.reset();
        thisCombatCard = null;
    }

    public void atBattleStartPreDraw() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard target = AbstractDungeon.player.drawPile.getRandomCard(AbstractDungeon.cardRandomRng);
                att(new ExhaustSpecificCardAction(target, AbstractDungeon.player.drawPile));
                AbstractDungeon.effectList.add(0, new ShowCardBrieflyEffect(target.makeStatEquivalentCopy()));
                thisCombatCard = target.makeStatEquivalentCopy();
            }
        });
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }

    @Override
    public String getDescription() {
        String result = super.getDescription();
        if (Wiz.isInCombat() && thisCombatCard != null) {
            result = result.replace("the #yExhausted card", FontHelper.colorString(thisCombatCard.name, "y"));
        }
        return result;
    }
}
