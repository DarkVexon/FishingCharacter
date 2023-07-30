package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.powers.LeviathanPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;

public class Leviathan extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("Leviathan");
    // intellij stuff power, self, uncommmon, , , , , , 

    public Leviathan() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new SpecialCurse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LeviathanPower(2));
    }

    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new SpecialCurse(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
    }
}