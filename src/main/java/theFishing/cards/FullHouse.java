package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFishing.powers.LambdaPower;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FullHouse extends AbstractFishingCard {
    public final static String ID = makeID("FullHouse");
    // intellij stuff power, self, rare, , , , , , 

    public FullHouse() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LambdaPower("Full House", AbstractPower.PowerType.BUFF, false, p, 1) {

            @Override
            public void atStartOfTurnPostDraw() {
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        ArrayList<String> dupesFound = new ArrayList<>();
                        for (AbstractCard q : AbstractDungeon.player.hand.group) {
                            if (!dupesFound.contains(q.cardID)) {
                                if (AbstractDungeon.player.hand.group.stream().anyMatch(d -> d.cardID.equals(q.cardID) && d != q)) {
                                    dupesFound.add(q.cardID);
                                }
                            }
                        }
                        for (int i = 0; i < amount; i++)
                            for (String s : dupesFound) {
                                AbstractCard q = CardLibrary.getCard(s);
                                q.upgrade();
                                q.setCostForTurn(0);
                                att(new MakeTempCardInHandAction(q, false));
                            }
                    }
                });
            }

            @Override
            public void updateDescription() {
                description = amount == 1 ? "Whenever you start your turn with two or more of the same card in your hand, add an Upgraded copy into your hand that costs 0 until played." : ("Whenever you start your turn with two or more of the same card in your hand, add #b" + amount + " Upgraded copies into your hand that cost 0 until played.");
            }
        });
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }
}