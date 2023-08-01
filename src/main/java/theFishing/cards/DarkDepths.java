package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.FishingMod;

import java.util.Iterator;

import static theFishing.FishingMod.makeID;

public class DarkDepths extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID(DarkDepths.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 7, 3, , , , 

    public DarkDepths() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        cardsToPreview = new Candle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (FishingMod.delvedThisTurn) {
                return true;
            }
            else {
                cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            }
        }
    }

    @Override
    public void onObtainCard() {
        float fractical = Settings.WIDTH / 2;
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Candle(), fractical, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeDamage(4);
    }
}