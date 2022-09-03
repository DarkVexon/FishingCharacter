package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.effects.RainbowExplosionEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;

public class ConfettiCannon extends AbstractFishingCard {
    public final static String ID = makeID("ConfettiCannon");
    // intellij stuff attack, all_enemy, rare, 7, 3, , , , 

    public ConfettiCannon() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (int i = 0; i < countCards(); i++) {
                   dmgTop(m, AttackEffect.NONE);
                   addToTop(new VFXAction(new RainbowExplosionEffect(m.hb.cX, m.hb.cY)));
                }
            }
        });
    }


    public static int countCards() {
        int count = 0;
        count += AbstractDungeon.player.drawPile.group.stream().filter(q -> isFoil(q)).count();
        count += AbstractDungeon.player.hand.group.stream().filter(q -> isFoil(q)).count();
        count += AbstractDungeon.player.discardPile.group.stream().filter(q -> isFoil(q)).count();
        return count / 3;
    }

    public void applyPowers() {
        super.applyPowers();
        int count = countCards();

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(3);
    }
}