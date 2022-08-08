package theFishing.cards.boxtoppers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ToxicGreed extends AbstractBoxTopper {
    public final static String ID = makeID("ToxicGreed");
    // intellij stuff skill, all_enemy, , , , , 4, 1

    public ToxicGreed() {
        super(ID, 2, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (randomMonster != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new PotionBounceEffect(p.hb.cY, p.hb.cX, randomMonster.hb.cX, this.hb.cY), 0.3F));
        }

        AbstractDungeon.actionManager.addToBottom(new BouncingFlaskAction(randomMonster, this.magicNumber, 1 + (Math.min(5, (AbstractDungeon.player.gold / 100)))));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}