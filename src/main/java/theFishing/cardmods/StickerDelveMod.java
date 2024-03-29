package theFishing.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theFishing.FishingMod;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;
import theFishing.boards.dailies.LandOfGiants;

import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class StickerDelveMod extends AbstractStickerModifier {
    public static final String ID = FishingMod.makeID(StickerDelveMod.class.getSimpleName());

    public StickerDelveMod() {
        super(StickerManager.StickerType.DELVE);
        offsetY = MathUtils.random(-175, -150);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        AbstractBoard.postInitDelveState(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                att(new EnterTheDungeonAction());
            }
        });
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new StickerDelveMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
