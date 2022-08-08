package theFishing.cards.boxtoppers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theFishing.cards.AbstractFishingCard;

import java.util.ArrayList;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class ZaHando extends AbstractBoxTopper {
    public final static String ID = makeID("ZaHando");
    // intellij stuff attack, enemy, special, 13, 5, , , , 

    public ZaHando() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 13;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!p.hand.isEmpty()) {
                    if (p.hand.size() % 2 == 0) {
                        att(new ExhaustSpecificCardAction(p.hand.group.get(p.hand.group.size() / 2), p.hand));
                        att(new ExhaustSpecificCardAction(p.hand.group.get(p.hand.group.size() / 2 - 1), p.hand));
                    } else {
                        att(new ExhaustSpecificCardAction(p.hand.group.get(p.hand.group.size() / 2), p.hand));
                    }
                }
            }
        });
    }

    @Override
    public void hover() {
        super.hover();
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                for (AbstractCard q : getMiddleCards()) {
                    q.glowColor = Color.RED.cpy();
                    q.beginGlowing();
                }
            }
        }
    }

    @Override
    public void unhover() {
        super.unhover();
        if (CardCrawlGame.isInARun()) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                for (AbstractCard q : getMiddleCards()) {
                    q.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
                    q.triggerOnGlowCheck();
                }
                AbstractDungeon.player.hand.applyPowers();
            }
        }
    }

    private ArrayList<AbstractCard> getMiddleCards() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> qCardList = new ArrayList<>();
        for (AbstractCard q : p.hand.group) {
            if (q != this)
                qCardList.add(q);
        }
        ArrayList<AbstractCard> coolStuffList = new ArrayList<>();
        if (!qCardList.isEmpty()) {
            if (qCardList.size() % 2 == 0) {
                coolStuffList.add(qCardList.get(qCardList.size() / 2 - 1));
                coolStuffList.add(qCardList.get(qCardList.size() / 2));
            } else {
                coolStuffList.add(qCardList.get(qCardList.size() / 2));
            }
        }
        return coolStuffList;
    }

    public void upp() {
        upgradeDamage(5);
    }
}