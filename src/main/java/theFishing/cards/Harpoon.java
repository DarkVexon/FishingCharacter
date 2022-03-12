package theFishing.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import theFishing.FishingMod;
import theFishing.actions.FatalRunnableAction;

import java.awt.*;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Harpoon extends AbstractFishingCard {
    public final static String ID = makeID("Harpoon");
    // intellij stuff attack, enemy, uncommon, 18, 5, , , 2, 1

    public Harpoon() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 18;
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect tde = new ThrowDaggerEffect(m.hb.cX, m.hb.cY);
        ReflectionHacks.setPrivate(tde, AbstractGameEffect.class, "color", Color.DARK_GRAY.cpy());
        atb(new VFXAction(tde));
        atb(new FatalRunnableAction(m, new DamageInfo(p, damage, damageTypeForTurn), () -> {
            FishingMod.nextCombatFish += magicNumber;
        }));
    }

    public void upp() {
        upgradeDamage(6);
    }
}