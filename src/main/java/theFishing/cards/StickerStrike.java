package theFishing.cards;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cardmods.StickerCardMod;
import theFishing.quest.QuestHelper;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class StickerStrike extends AbstractFishingCard {
    public final static String ID = makeID("StickerStrike");
    // intellij stuff attack, enemy, uncommon, 7, 2, , , 7, 2

    public StickerStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (upgraded) {
            atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
                for (AbstractCard c : cards) {
                    CardModifierManager.addModifier(c, new StickerCardMod());
                    c.superFlash(Color.GOLD.cpy());
                }
            }));
        } else {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractCard tar = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    CardModifierManager.addModifier(tar, new StickerCardMod());
                    tar.superFlash(Color.GOLD.cpy());
                }
            });
        }
    }

    public void upp() {
        upgradeDamage(2);
        uDesc();
    }
}