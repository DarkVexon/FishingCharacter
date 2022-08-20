package theFishing.cards.fish.basefish;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.makeInHand;

public class SeaMonster extends AbstractFishCard {
    public final static String ID = makeID("SeaMonster");
    // intellij stuff skill, none, special, , , , , , 

    public SeaMonster() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            makeInHand(AbstractFishCard.returnRandomFish());
        }
    }
}