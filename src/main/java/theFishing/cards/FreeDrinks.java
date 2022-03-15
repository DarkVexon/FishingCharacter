package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class FreeDrinks extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("FreeDrinks");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public FreeDrinks() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -magicNumber)));
    }

    @Override
    public void onObtainCard() {
        if (!(AbstractDungeon.getCurrRoom() instanceof MonsterRoom) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            AbstractDungeon.getCurrRoom().rewards.clear();
        }
        AbstractDungeon.getCurrRoom().addPotionToRewards(PotionHelper.getRandomPotion());
        AbstractDungeon.combatRewardScreen.open(cardStrings.EXTENDED_DESCRIPTION[0]);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}