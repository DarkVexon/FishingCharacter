package theFishing.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class FeedingFrenzy extends AbstractFishingCard {
    public final static String ID = makeID(FeedingFrenzy.class.getSimpleName());

    public FeedingFrenzy() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : p.drawPile.group) {
            if (c instanceof AbstractFishCard) {
                addToBot(new ExhaustSpecificCardAction(c, p.drawPile));
                count++;
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c instanceof AbstractFishCard) {
                addToBot(new ExhaustSpecificCardAction(c, p.hand));
                count++;
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof AbstractFishCard) {
                addToBot(new ExhaustSpecificCardAction(c, p.discardPile));
                count++;
            }
        }
        for (int i = 0; i < count; i++)
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster) null, true, AbstractDungeon.cardRandomRng);
                    att(new DamageAction(q, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.FIRE));
                    att(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY)));
                }
            });
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(2);
    }
}