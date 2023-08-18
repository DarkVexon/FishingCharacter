package theFishing.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AnimatedSlashEffect;
import theFishing.FishingMod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static theFishing.util.Wiz.att;

public class MopUpAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(FishingMod.makeID("MopUpAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private final AbstractMonster target;
    private final int damage;
    private final DamageInfo.DamageType damageTypeForTurn;


    public MopUpAction(AbstractMonster target, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.target = target;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
        this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        ArrayList<AbstractCard> hand = AbstractDungeon.player.hand.group;
        if (this.duration == this.startDuration) {
            if (hand.size() != 0) {
                if (hand.size() == 1) {
                    mopUpSelectedCard(hand.stream().collect(Collectors.toList()));

                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                    this.isDone = true;
                } else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            mopUpSelectedCard(AbstractDungeon.handCardSelectScreen.selectedCards.group);

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
            this.isDone = true;
        } else {
            this.tickDuration();
        }
    }

    private void mopUpSelectedCard(List<AbstractCard> cards) {
        if (cards.get(0).type == AbstractCard.CardType.STATUS || cards.get(0).type == AbstractCard.CardType.CURSE || cards.get(0).color == AbstractCard.CardColor.CURSE) {
            att(new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
            att(new VFXAction(new AnimatedSlashEffect(target.hb.cX, target.hb.cY - 30.0F * Settings.scale, 500.0F, 200.0F, 290.0F, 3.0F, Color.FOREST, Color.GREEN)));
            att(new SFXAction("ATTACK_FAST", 0.2F));
            att(new SFXAction("ATTACK_WHIFF_2", 0.3F));
        }
        AbstractDungeon.player.hand.moveToExhaustPile(cards.get(0));
    }
}
