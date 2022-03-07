package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.Anchor;

import static theFishing.FishingMod.makeID;

public class AnchorsAweigh extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("AnchorsAweigh");
    // intellij stuff skill, self, rare, , , 10, 5, , 

    public AnchorsAweigh() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(current_x, current_y, new Anchor());
    }

    public void upp() {
        upgradeBlock(5);
    }
}