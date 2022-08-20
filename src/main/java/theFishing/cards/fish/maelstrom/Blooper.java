package theFishing.cards.fish.maelstrom;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class Blooper extends AbstractFishCard {
    public static final String ID = makeID("Blooper");

    public Blooper() {
        super(ID, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> {
            applyToEnemy(q, new StrengthPower(q, -magicNumber));
            if (!q.hasPower(ArtifactPower.POWER_ID))
                applyToEnemy(q, new GainStrengthPower(q, magicNumber));
        });
    }
}
