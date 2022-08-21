package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class NostalgicStrike extends AbstractFishingCard {
    public final static String ID = makeID("NostalgicStrike");
    // intellij stuff attack, enemy, common, 5, 3, , , , 

    public NostalgicStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.discardPile.isEmpty()) {
                    AbstractCard card = AbstractDungeon.player.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractDungeon.player.hand.addToHand(card);
                            card.lighten(false);
                            AbstractDungeon.player.discardPile.removeCard(card);
                            AbstractDungeon.player.hand.refreshHandLayout();
                        }
                    });
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}