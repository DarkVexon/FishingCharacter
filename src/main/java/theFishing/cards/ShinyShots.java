package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ShinyShots extends AbstractFishingCard {
    public final static String ID = makeID("ShinyShots");
    // intellij stuff attack, enemy, common, 5, 1, 2, 1, , 

    public ShinyShots() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int amt = (int) AbstractDungeon.player.hand.group.stream().filter(q -> FoilPatches.isFoil(q)).count();
                for (int i = 0; i < amt; i++) {
                    dmgTop(m, AttackEffect.BLUNT_HEAVY);
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(2);
    }
}