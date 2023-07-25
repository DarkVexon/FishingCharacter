package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.util.ExportSheet;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static theFishing.FishingMod.makeID;

public class Strike extends AbstractFishingCard {
    public final static String ID = makeID("Strike");

    public Strike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(ExportSheet.export()), null);
    }

    public void upp() {
        upgradeDamage(3);
    }
}