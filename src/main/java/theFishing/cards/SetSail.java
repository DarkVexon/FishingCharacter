package theFishing.cards;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.actions.SetSailAction;

import java.util.Collections;
import java.util.List;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class SetSail extends AbstractFishingCard {
    public final static String ID = makeID("SetSail");
    // intellij stuff skill, self, common, , , 4, , , 

    public SetSail() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SetSailAction(CardType.ATTACK));
        if (upgraded) {
            forAllMonstersLiving(q -> applyToEnemy(q, new VulnerablePower(q, 1, false)));
        } else {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster q = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                    applyToEnemyTop(q, new VulnerablePower(q, magicNumber, false));
                }
            });
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(new TooltipInfo(BaseMod.getKeywordTitle(makeID("fish")), BaseMod.getKeywordDescription(makeID("fish"))));
    }

    public void upp() {
        uDesc();
    }
}