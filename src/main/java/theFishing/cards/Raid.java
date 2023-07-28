package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theFishing.FishingMod;

import static theFishing.FishingMod.makeID;

public class Raid extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("RaidNight");
    // intellij stuff attack, enemy, uncommon, 7, 3, , , , 

    public Raid() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (int i = 0; i < FishingMod.timesCompletedThisCombat; i++) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    @Override
    public void onObtainCard() {
        float fractical = Settings.WIDTH / 3;
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new PartyMember(), fractical, Settings.HEIGHT / 2));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new PartyMember(), fractical * 2, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeDamage(3);
    }
}