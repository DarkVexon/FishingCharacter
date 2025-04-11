package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.powers.LunchBoxPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Lunchbox extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("Lunchbox");
    // intellij stuff skill, self, uncommon, , , 6, 3, , 

    public Lunchbox() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new LunchBoxPower(1));
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.player.heal(4);
    }

    public void upp() {
        upgradeBlock(3);
    }
}