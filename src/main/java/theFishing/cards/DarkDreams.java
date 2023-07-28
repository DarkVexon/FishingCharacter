package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.patch.OnRestCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class DarkDreams extends AbstractFishingCard implements OnRestCard {
    public final static String ID = makeID("DarkDreams");
    // intellij stuff skill, enemy, uncommon, , , , , 3, 2

    public DarkDreams() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        atb(new EnterTheDungeonAction());
    }

    @Override
    public void onRest() {
        upgrade();
        float x = MathUtils.random(0.1F, 0.9F) * (float) Settings.WIDTH;
        float y = MathUtils.random(0.2F, 0.8F) * (float) Settings.HEIGHT;
        AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(makeStatEquivalentCopy(), x, y));
        AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}