package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class RingTheBell extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("RingTheBell");

    public RingTheBell() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 13;
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("BELL"));
        atb(new AllEnemyLoseHPAction(magicNumber));
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2F, Settings.HEIGHT / 2F));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}