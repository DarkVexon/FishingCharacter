package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class ExtraTentacle extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("ExtraTentacle");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , , 

    public ExtraTentacle() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        cardsToPreview = new Clumsy();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new SelectCardsAction(p.drawPile.group, 1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> cards.forEach(q -> att(new ExhaustSpecificCardAction(q, p.drawPile, false)))));
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Clumsy(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
    }
}