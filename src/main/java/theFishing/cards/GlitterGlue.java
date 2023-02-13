package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.patch.foil.FoilPatches.isFoil;
import static theFishing.patch.foil.FoilPatches.makeFoil;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.atb;

public class GlitterGlue extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("GlitterGlue");
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public GlitterGlue() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ATTACK_WHIFF_2", 0.3F));
        atb(new SFXAction("ATTACK_FAST", 0.2F));
        atb(new VFXAction(new AnimatedSlashEffect(m.hb.cX, m.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.VIOLET, Color.PINK)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(4);
    }


    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!isFoil(c)) {
                upgradableCards.add(c);
            }
        }
        ArrayList<AbstractCard> foiledCards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            if (!upgradableCards.isEmpty()) {
                AbstractCard tar = Wiz.getRandomItem(upgradableCards);
                if (tar != null) {
                    makeFoil(tar);
                    upgradableCards.remove(tar);
                    foiledCards.add(tar);
                }
            }
        }

        if (foiledCards.size() == 2) {
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(foiledCards.get(0).makeStatEquivalentCopy(), Settings.WIDTH / 3.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(foiledCards.get(1).makeStatEquivalentCopy(), (Settings.WIDTH / 3.0F * 2f), Settings.HEIGHT / 2.0F));
        } else if (foiledCards.size() == 1) {
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(foiledCards.get(0).makeStatEquivalentCopy()));
        }
    }
}