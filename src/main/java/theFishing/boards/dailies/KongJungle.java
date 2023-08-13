package theFishing.boards.dailies;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.boards.BoardEffect;
import theFishing.cards.Banana;
import theFishing.powers.NextSkillFreePower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.makeInHandTop;

public class KongJungle extends AbstractBoard {
    public static final String ID = FishingMod.makeID(KongJungle.class.getSimpleName());
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    public KongJungle() {
        super(ID, TEXT[0]);
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Banana())));
        effects.add(new BoardEffect(TEXT[1], () -> makeInHandTop(new Banana())));
        effects.add(new BoardEffect(TEXT[2], () -> applyToSelfTop(new NextSkillFreePower(1))));
    }
}
