package theFishing.cards;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import theFishing.FishingMod;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;

import static theFishing.FishingMod.makeID;
import static theFishing.FishingMod.makeImagePath;

public class Wander extends AbstractFishingCard implements MasterDeckButtonCard {
    private static final TextureAtlas.AtlasRegion buttonTex = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/removal.png")));
    public final static String ID = makeID("Wander");
    // intellij stuff attack, all_enemy, common, 9, 1, , , 1, 1

    public Wander() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 18;
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void upp() {
        upgradeDamage(4);
        upgradeBlock(2);
    }

    @Override
    public void onButtonPressed() {
        untip();
        unhover();
        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(this));
        FishingMod.toRemove = this;
    }

    @Override
    public TextureAtlas.AtlasRegion buttonArt() {
        return buttonTex;
    }
}