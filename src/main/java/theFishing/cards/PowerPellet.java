package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

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
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                forAllCardsInList((card) -> {
                    if (card.cardID.equals(WakaWaka.ID)) {
                        card.flash();
                        card.baseDamage += magicNumber;
                        card.applyPowers();
                    }
                }, getAllCardsInCardGroups(true, false));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}