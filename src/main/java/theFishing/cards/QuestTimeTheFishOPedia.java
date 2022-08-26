package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.quest.quests.TheFishOPedia;
import theFishing.relics.MaelstromAnkh;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class QuestTimeTheFishOPedia extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheFishOPedia");
    // intellij stuff skill, self, uncommon, , , , , , 

    public QuestTimeTheFishOPedia() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<String> fishList = new ArrayList<>();
        if (AbstractDungeon.player.hasRelic(MaelstromAnkh.ID)) {
            fishList.addAll(AbstractFishCard.maelstromFishList.keySet());
        } else {
            fishList.addAll(AbstractFishCard.weightedFishList.keySet());
        }
        ArrayList<AbstractCard> possCards = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            possCards.add(CardLibrary.getCard(fishList.remove(AbstractDungeon.cardRandomRng.random(fishList.size() - 1))));
        }
        atb(new SelectCardsCenteredAction(possCards, 1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            att(new MakeTempCardInHandAction(cards.get(0)));
        }));
        atb(new AcceptQuestAction(new TheFishOPedia()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}