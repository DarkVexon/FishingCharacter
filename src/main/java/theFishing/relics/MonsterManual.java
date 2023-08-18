package theFishing.relics;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theFishing.TheFishing;
import theFishing.cardmods.StickerDelveMod;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;

public class MonsterManual extends AbstractAdventurerRelic {
    public static final String ID = makeID(MonsterManual.class.getSimpleName());

    public MonsterManual() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void onEquip() {
        ArrayList<AbstractCard> possCards = new ArrayList<>();
        possCards.addAll(AbstractDungeon.player.masterDeck.group);
        possCards.removeIf(q -> q.cost == -2);
        AbstractCard tar = Wiz.getRandomItem(possCards, AbstractDungeon.cardRandomRng);
        CardModifierManager.addModifier(tar, new StickerDelveMod());
        AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(tar.makeStatEquivalentCopy()));
    }
}
