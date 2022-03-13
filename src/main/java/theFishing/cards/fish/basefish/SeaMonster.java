package theFishing.cards.fish.basefish;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class SeaMonster extends AbstractFishCard {
    public final static String ID = makeID("SeaMonster");
    // intellij stuff skill, none, special, , , , , , 

    public SeaMonster() {
        super(ID, 0, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.NONE);
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> foundCards = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            foundCards.add(AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy());
        }
        if (upgraded) {
            for (AbstractCard c : foundCards) {
                c.upgrade();
            }
        }
        for (AbstractCard c : foundCards) {
            CardModifierManager.addModifier(c, new ExhaustMod());
            c.setCostForTurn(0);
            atb(new MakeTempCardInHandAction(c, true));
        }
    }

    public void upp() {
        uDesc();
    }
}