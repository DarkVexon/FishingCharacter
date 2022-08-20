package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;

public class FlyingFish extends AbstractFishCard {
    public final static String ID = makeID("FlyingFish");
    // intellij stuff skill, self, , , , , , 

    public FlyingFish() {
        super(ID, CardType.SKILL, CardTarget.ENEMY);
        baseDamage = 1;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 5; i++) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }
}