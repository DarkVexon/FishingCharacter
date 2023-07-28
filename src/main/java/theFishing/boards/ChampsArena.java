package theFishing.boards;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theFishing.util.Wiz;

import static theFishing.util.Wiz.*;

public class ChampsArena extends AbstractBoard {
    public static final String ID = "fishing:ChampsArena";
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public ChampsArena() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], TEXT[2], () -> makeInHandTop(new Shiv())));
        effects.add(new BoardEffect(TEXT[3], TEXT[4], () -> {
            AbstractMonster tar = Wiz.getFrontmostEnemy();
            applyToEnemyTop(tar, new VulnerablePower(tar, 1, false));
            applyToEnemyTop(tar, new WeakPower(tar, 1, false));
        }));
        effects.add(new BoardEffect(TEXT[5], TEXT[6], () -> applyToSelfTop(new StrengthPower(AbstractDungeon.player, 1))));
    }
}
