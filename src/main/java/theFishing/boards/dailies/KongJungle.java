package theFishing.boards.dailies;

import theFishing.FishingMod;
import theFishing.boards.AbstractBoard;
import theFishing.cards.Banana;
import theFishing.powers.NextSkillFreePower;

import static theFishing.util.Wiz.applyToSelfTop;
import static theFishing.util.Wiz.makeInHandTop;

public class KongJungle extends AbstractBoard {
    public static final String ID = FishingMod.makeID(KongJungle.class.getSimpleName());

    public KongJungle() {
        super(ID);
        effects.add(() -> makeInHandTop(new Banana()));
        effects.add(() -> makeInHandTop(new Banana()));
        effects.add(() -> applyToSelfTop(new NextSkillFreePower(1)));
    }
}
