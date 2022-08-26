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
import java.util.HashMap;
import java.util.Map;

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
        AbstractFishCard.setupLists();
        HashMap<String, Integer> dupeList = new HashMap<>();
        if (AbstractDungeon.player.hasRelic(MaelstromAnkh.ID)) {
            for (Map.Entry<String, Integer> e : AbstractFishCard.maelstromFishList.entrySet()) {
                dupeList.put(e.getKey(), e.getValue());
            }
        } else {
            for (Map.Entry<String, Integer> e : AbstractFishCard.weightedFishList.entrySet()) {
                dupeList.put(e.getKey(), e.getValue());
            }
        }
        ArrayList<AbstractCard> possCards = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            int fishRoll = AbstractDungeon.cardRandomRng.random(1,
                    dupeList.keySet().stream()
                            .mapToInt(f -> dupeList.get(f))
                            .sum()
            );

            for (String fishy : dupeList.keySet()) {
                fishRoll -= dupeList.get(fishy);
                if (fishRoll <= 0) {
                    possCards.add(CardLibrary.getCard(fishy).makeCopy());
                    dupeList.remove(fishy);
                    break;
                }
            }
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