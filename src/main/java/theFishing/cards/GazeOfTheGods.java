package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;

public class GazeOfTheGods extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("GazeOfTheGods");
    // intellij stuff skill, enemy, rare, , , , , 30, 10

    public GazeOfTheGods() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        AbstractCard q = new Puzzle();
        makeFoil(q);
        cardsToPreview = q;
        baseDamage = 7;
        baseMagicNumber = magicNumber = 4;
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
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(FoilPatches::isFoil).count();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(FoilPatches::isFoil).count();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void onObtainCard() {
        float fractical = Settings.WIDTH / 2F;
        AbstractCard q = new Puzzle();
        makeFoil(q);
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(q, fractical, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}