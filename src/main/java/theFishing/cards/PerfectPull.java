package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class PerfectPull extends AbstractFishingCard {
    public final static String ID = makeID("PerfectPull");
    // intellij stuff attack, all_enemy, common, 5, 2, , , , 

    public PerfectPull() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> possCards = new ArrayList<>();
                AbstractDungeon.player.hand.group.stream().forEach(q -> {
                    if (FoilPatches.isFoil(q)) {
                        possCards.add(q);
                    }
                });
                if (!possCards.isEmpty()) {
                    AbstractCard target = Wiz.getRandomItem(possCards, AbstractDungeon.cardRandomRng);
                    target.superFlash();
                    target.setCostForTurn(0);
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}