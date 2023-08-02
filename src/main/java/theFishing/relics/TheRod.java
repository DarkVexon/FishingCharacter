package theFishing.relics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.MadnessAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.TheFishing;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TheRod extends AbstractAdventurerRelic {
    public static final String ID = makeID(TheRod.class.getSimpleName());

    public TheRod() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atBattleStart() {
        atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard target;

                ArrayList<AbstractCard> unupgraded = new ArrayList<>();
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    if (q.canUpgrade()) {
                        unupgraded.add(q);
                    }
                }
                if (unupgraded.isEmpty()) {
                    target = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                } else {
                    target = Wiz.getRandomItem(unupgraded);
                }

                if (target.canUpgrade()) {
                    target.upgrade();
                }
                target.freeToPlayOnce = true;
                target.superFlash(Color.GOLD.cpy());
            }
        });
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
