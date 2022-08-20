package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Whaling extends AbstractFishingCard {
    public final static String ID = makeID("Whaling");
    // intellij stuff power, self, uncommon, , , , , , 

    public Whaling() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, 1));
        if (p.gold > 200) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    p.loseGold(secondMagic);
                    applyToSelfTop(new StrengthPower(p, magicNumber));
                    att(new VFXAction(new InflameEffect(p)));
                }
            });
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.player.gold > 200 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}