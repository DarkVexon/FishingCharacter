package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class MerchantsStrike extends AbstractFishingCard implements OnBuyRelicCard {
    public final static String ID = makeID("MerchantsStrike");
    // intellij stuff attack, enemy, uncommon, 6, 1, , , 9, 3

    public MerchantsStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseBlock = 2;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        forAllMonstersLiving(q -> blck());
    }

    @Override
    public void onBuyRelic(AbstractRelic r, int price) {
        if (!upgraded) {
            upgrade();
            float x = MathUtils.random(0.1F, 0.9F) * (float) Settings.WIDTH;
            float y = MathUtils.random(0.2F, 0.8F) * (float) Settings.HEIGHT;
            AbstractDungeon.topLevelEffects.add(new ShowCardBrieflyEffect(makeStatEquivalentCopy(), x, y));
            AbstractDungeon.topLevelEffects.add(new UpgradeShineEffect(x, y));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(1);
    }
}