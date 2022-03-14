package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FreeDrinks extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("FreeDrinks");
    // intellij stuff attack, self_and_enemy, uncommon, 6, 2, 3, , , 

    public FreeDrinks() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseBlock = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToSelf(new DrawCardNextTurnPower(p, 1));
    }

    private static boolean hasEmptyPotionSlot() {
        for (AbstractPotion p : AbstractDungeon.player.potions) {
            if (p instanceof PotionSlot) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onObtainCard() {
        if (hasEmptyPotionSlot()) {
            AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(1);
    }
}