package theFishing.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.actions.SetSailAction;
import theFishing.patch.foil.FoilTooltips1;

import java.util.Collections;
import java.util.List;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.atb;

public class SetSail extends AbstractFishingCard {
    public final static String ID = makeID("SetSail");
    // intellij stuff skill, self, common, , , 4, , , 

    public SetSail() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SetSailAction(CardType.ATTACK));
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(FoilTooltips1.UI_STRINGS.TEXT[2]), BaseMod.getKeywordDescription(FoilTooltips1.UI_STRINGS.TEXT[2])));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}