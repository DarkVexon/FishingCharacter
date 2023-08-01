package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

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
                ArrayList<AbstractCard> potential = new ArrayList<>();
                potential.addAll(p.discardPile.group.stream().filter(q -> q.cost == 0 || q.freeToPlayOnce).collect(Collectors.toList()));
                if (!potential.isEmpty()) {
                    p.discardPile.moveToHand(Wiz.getRandomItem(potential));
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}