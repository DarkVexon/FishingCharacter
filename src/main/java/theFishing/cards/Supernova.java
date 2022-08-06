package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import theFishing.patch.foil.FoilPatches;


import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Supernova extends AbstractFishingCard {
    public final static String ID = makeID("Supernova");
    // intellij stuff attack, all_enemy, rare, 14, 4, , , , 

    public Supernova() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 11;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = 0;
                for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (FoilPatches.isFoil(q)) {
                        x += 1;
                    }
                }
                for (int i = 0; i < x; i++) {
                    atb(new VFXAction(new ShockWaveEffect(p.hb.cX, p.hb.cY, new Color(MathUtils.random(1.0f), MathUtils.random(1.0f), MathUtils.random(1.0f), 1.0f), ShockWaveEffect.ShockWaveType.NORMAL)));
                    allDmg(AttackEffect.FIRE);
                }
            }
        });
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (FoilPatches.isFoil(q)) {
                count += 1;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(3);
    }
}