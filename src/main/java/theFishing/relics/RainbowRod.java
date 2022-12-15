package theFishing.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.TheFishing;

import static theFishing.FishingMod.makeID;

public class RainbowRod extends AbstractAdventurerRelic {
    public static final String ID = makeID("RainbowRod");

    public RainbowRod() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, TheFishing.Enums.FISHING_COLOR);
    }

    @Override
    public void atTurnStart() {
        counter = 2;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (counter > 0 && !drawnCard.upgraded && drawnCard.canUpgrade()) {
            counter -= 1;
            drawnCard.upgrade();
            drawnCard.superFlash();
            drawnCard.applyPowers();
            if (counter == 0) {
                counter = -1;
            }
        }
    }

    @Override
    public void onEquip() {
        if (AbstractDungeon.player instanceof TheFishing) {
            ((TheFishing) AbstractDungeon.player).onEquipRainbowRod();
        }
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(TheRod.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(TheRod.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(TheRod.ID);
    }

    @Override
    public String getUpdatedDescription() {
        // Colorize the starter relic's name
        String name = new TheRod().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(FishingMod.characterColor.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length() - 1);
        sb.append("[#").append(FishingMod.characterColor.toString()).append("]");

        return DESCRIPTIONS[0] + sb + DESCRIPTIONS[1];
    }
}
