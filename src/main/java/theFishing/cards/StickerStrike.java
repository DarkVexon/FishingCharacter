package theFishing.cards;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cardmods.StickerHPLossMod;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class StickerStrike extends AbstractFishingCard {
    public final static String ID = makeID("StickerStrike");
    // intellij stuff attack, enemy, uncommon, 7, 2, , , 7, 2

    public StickerStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 1;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.hand.isEmpty()) {
                    ArrayList<AbstractCard> validCards = new ArrayList<>();
                    validCards.addAll(AbstractDungeon.player.hand.group);
                    ArrayList<AbstractCard> toModify = new ArrayList<>();
                    for (int i = 0; i < magicNumber; i++) {
                        if (!validCards.isEmpty()) {
                            AbstractCard next = Wiz.getRandomItem(validCards, AbstractDungeon.cardRandomRng);
                            validCards.remove(next);
                            toModify.add(next);
                        }
                    }
                    for (AbstractCard tar : toModify) {
                        CardModifierManager.addModifier(tar, new StickerHPLossMod());
                        tar.superFlash(Color.GOLD.cpy());
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}