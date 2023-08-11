package theFishing.cards;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

@NoCompendium
public class StrikeX extends AbstractFishingCard {
    public final static String ID = makeID(StrikeX.class.getSimpleName());

    public StrikeX() {
        super(ID, -1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            }
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}