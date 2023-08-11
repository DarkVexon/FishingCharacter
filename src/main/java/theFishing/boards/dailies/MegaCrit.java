package theFishing.boards.dailies;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cardmods.StickerEtherealMod;
import theFishing.cardmods.StickerExhaustMod;
import theFishing.cardmods.StickerRetainMod;

import static theFishing.util.Wiz.makeInHand;
import static theFishing.util.Wiz.makeInHandTop;

public class MegaCrit extends AbstractBoard {
    public static final String ID = FishingMod.makeID(MegaCrit.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public MegaCrit() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[2], () -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK);
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        }));
        effects.add(new BoardEffect(TEXT[3], () -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK);
            toMake.upgrade();
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        }));
        effects.add(new BoardEffect(TEXT[4], () -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER);
            toMake.updateCost(-999);
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        }));
    }

    @Override
    public String getSpecialRule() {
        return TEXT[1];
    }
}
