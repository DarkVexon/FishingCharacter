package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.DamagePlusWallopVFXAction;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class ShinyShots extends AbstractFishingCard {
    public final static String ID = makeID("ShinyShots");
    // intellij stuff attack, enemy, common, 5, 1, 2, 1, , 

    public ShinyShots() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new DamagePlusWallopVFXAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    if (FoilPatches.isFoil(q)) {
                        att(new GainBlockAction(AbstractDungeon.player, block));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(1);
        upgradeBlock(1);
    }
}