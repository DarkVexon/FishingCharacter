package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class StickerCardMod extends AbstractCardModifier {

    public static final String ID = FishingMod.makeID(StickerCardMod.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private int hpLoss;

    public StickerCardMod(int hpLoss) {
        this.hpLoss = hpLoss;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            for (AbstractCardModifier mod : CardModifierManager.getModifiers(card, ID)) {
                if (mod instanceof StickerCardMod) {
                    ((StickerCardMod) mod).hpLoss += hpLoss;
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + uiStrings.TEXT[0] + hpLoss + uiStrings.TEXT[1];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                att(new LoseHPAction(AbstractDungeon.getRandomMonster(), AbstractDungeon.player, hpLoss));
            }
        });
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StickerCardMod(hpLoss);
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
