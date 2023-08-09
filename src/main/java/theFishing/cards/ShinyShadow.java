package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import theFishing.actions.PlayFromPileAction;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ShinyShadow extends AbstractFishingCard {
    public final static String ID = makeID("ShinyShadow");
    // intellij stuff attack, enemy, common, 19, 6, , , , 

    public ShinyShadow() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new DarkOrbActivateEffect(m.hb.cX, m.hb.cY));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new SelectCardsAction(AbstractDungeon.player.drawPile.group, 1, cardStrings.EXTENDED_DESCRIPTION[0], false, (c) -> FoilPatches.isFoil(c), (cards) -> {
            for (AbstractCard q : cards) {
                att(new PlayFromPileAction(q, AbstractDungeon.player.drawPile));
            }
        }));
    }

    public void upp() {
        upgradeDamage(5);
    }
}