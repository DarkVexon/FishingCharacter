package theFishing.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import theFishing.cards.WakaWaka;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.topDeck;

public class PowerPelletPower extends AbstractAdventurerPower {
    public static String ID = makeID(PowerPelletPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public PowerPelletPower(int amount) {
        super(ID, powerStrings.NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.cardID.equals(WakaWaka.ID)) {
            return damage + amount;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
    }
}
