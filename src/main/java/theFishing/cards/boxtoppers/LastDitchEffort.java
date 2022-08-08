package theFishing.cards.boxtoppers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class LastDitchEffort extends AbstractBoxTopper {
    public final static String ID = makeID("LastDitchEffort");
    // intellij stuff attack, enemy, 13, 3, , , , 

    public LastDitchEffort() {
        super(ID, 2, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 2F) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 3F) {
            dmg(m, AbstractGameAction.AttackEffect.SMASH);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 3F) {
            glowColor = Color.RED.cpy();
        }
        else if (AbstractDungeon.player.currentHealth < AbstractDungeon.player.maxHealth / 2F) {
            glowColor = Color.ORANGE.cpy();
        }
        else {
            glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}