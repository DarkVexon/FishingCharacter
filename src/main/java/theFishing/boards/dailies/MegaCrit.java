package theFishing.boards.dailies;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cardmods.StickerRetainMod;

import static theFishing.util.Wiz.makeInHand;
import static theFishing.util.Wiz.makeInHandTop;

public class MegaCrit extends AbstractBoard {
    public static final String ID = FishingMod.makeID(MegaCrit.class.getSimpleName());

    public MegaCrit() {
        super(ID);
        effects.add(() -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK);
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        });
        effects.add(() -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK);
            toMake.upgrade();
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        });
        effects.add(() -> {
            AbstractCard toMake = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER);
            toMake.updateCost(-999);
            CardModifierManager.addModifier(toMake, new StickerRetainMod());
            makeInHandTop(toMake);
        });
    }
}
