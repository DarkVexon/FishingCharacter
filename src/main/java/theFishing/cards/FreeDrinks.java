package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.Sozu;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class FreeDrinks extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("FreeDrinks");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public FreeDrinks() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -magicNumber)));
    }

    @Override
    public void onObtainCard() {
        if (AbstractDungeon.player.potions.stream().anyMatch(c -> c instanceof PotionSlot)) {
            if (AbstractDungeon.player.hasRelic(Sozu.ID)) {
                AbstractDungeon.player.getRelic(Sozu.ID).flash();
            } else {
                AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}