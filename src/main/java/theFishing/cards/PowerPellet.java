package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class PowerPellet extends AbstractFishingCard {
    public final static String ID = makeID("PowerPellet");
    // intellij stuff skill, self, special, , , , , 6, 3

    public PowerPellet() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 6;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(makeID("WAKA_WAKA")));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                forAllCardsInList((card) -> {
                    if (card.cardID.equals(WakaWaka.ID)) {
                        card.baseDamage += magicNumber;
                        card.applyPowers();
                        if (AbstractDungeon.player.hand.contains(card)) {
                            card.superFlash();
                        }
                    }
                }, getAllCardsInCardGroups(true, false));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}