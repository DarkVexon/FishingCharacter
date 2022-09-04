package theFishing.cards;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.DeckToTopOfDeckAction;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;

public class Wander extends AbstractFishingCard {
    private static final TextureAtlas.AtlasRegion buttonTex = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/removal.png")));
    public final static String ID = makeID("Wander");
    // intellij stuff attack, all_enemy, common, 9, 1, , , 1, 1

    public Wander() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DeckToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeDamage(3);
    }
}