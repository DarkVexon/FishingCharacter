package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.makeInHandTop;

public class GazeOfTheGods extends AbstractFishingCard {
    public final static String ID = makeID("GazeOfTheGods");
    // intellij stuff skill, enemy, rare, , , , , 30, 10

    public GazeOfTheGods() {
        super(ID, 4, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        AbstractCard q = new StarShard();
        makeFoil(q);
        cardsToPreview = q;
        exhaust = true;
    }

    public static String scream() {
        int roll = MathUtils.random(1);
        if (roll == 0) {
            return "VO_NEMESIS_2A";
        } else {
            return "VO_NEMESIS_2B";
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new SFXAction(scream()));
//        vfx(new GiantEyeEffect(m.hb.cX, m.hb.cY + 300.0F * Settings.scale, new Color(0.98431372549F, 0.94901960784F, 0.21176470588F, 0.0F)));
//        vfx(new LightningEffect(m.drawX, m.drawY), 0.05F);
//        atb(new LoseHPAction(m, p, magicNumber));
    }

    @Override
    public void triggerOnExhaust() {
        CardCrawlGame.sound.play(scream(), 0.1F);
        AbstractCard c = new StarShard();
        if (upgraded) {
            c.upgrade();
        }
        FoilPatches.makeFoil(c);
        makeInHandTop(c);
    }

    public void upp() {
        uDesc();
        AbstractCard q = new StarShard();
        q.upgrade();
        makeFoil(q);
        cardsToPreview = q;
    }
}