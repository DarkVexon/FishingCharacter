package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theFishing.actions.SetSailAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemyTop;
import static theFishing.util.Wiz.atb;

public class SetSail extends AbstractFishingCard {
    public final static String ID = makeID("SetSail");
    // intellij stuff skill, self, common, , , 4, , , 

    public SetSail() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SetSailAction(1));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractMonster q = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                applyToEnemyTop(q, new VulnerablePower(q, magicNumber, false));
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}