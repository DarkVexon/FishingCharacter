package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.FishingMod;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class StickerHPLossMod extends AbstractStickerModifier {
    public static final String ID = FishingMod.makeID(StickerHPLossMod.class.getSimpleName());

    public StickerHPLossMod() {
        super(StickerManager.StickerType.HPLOSS);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster q = AbstractDungeon.getRandomMonster();
                if (q != null)
                    att(new LoseHPAction(q, AbstractDungeon.player, 4));
            }
        });
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        StickerManager.renderSticker(sb, StickerManager.StickerType.HPLOSS, card, offsetX, offsetY);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StickerHPLossMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
