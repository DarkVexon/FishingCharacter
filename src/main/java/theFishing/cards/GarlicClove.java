package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class GarlicClove extends AbstractFishingCard {
    public final static String ID = makeID("GarlicClove");
    // intellij stuff skill, self, uncommon, , , , , 4, 2

    public GarlicClove() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new MiracleEffect(Color.CHARTREUSE, Color.LIME, "HEAL_3")));
        atb(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
        atb(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        if (AbstractDungeon.player.masterDeck.group.stream().noneMatch(c -> c.cardID.equals(Bite.ID))) {
            atb(new HealAction(p, p, magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}