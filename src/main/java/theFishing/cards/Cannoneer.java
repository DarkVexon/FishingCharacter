package theFishing.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class Cannoneer extends AbstractFishingCard {
    public final static String ID = makeID("Cannoneer");
    // intellij stuff attack, all_enemy, uncommon, 8, 2, , , , 

    public Cannoneer() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {

                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
                        AbstractGameEffect tde = new ThrowDaggerEffect(q.hb.cX, q.hb.cY);
                        ReflectionHacks.setPrivateInherited(tde, AbstractGameEffect.class, "color", Color.BLACK.cpy());
                        att(new DamageAction(q, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.FIRE));
                        att(new VFXAction(tde));
                    }
                });
            }
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
    }
}