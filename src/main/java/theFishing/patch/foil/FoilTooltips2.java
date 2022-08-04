package theFishing.patch.foil;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static theFishing.FishingMod.makeID;

@SpirePatch(clz = SingleCardViewPopup.class, method = "renderTips")
public class FoilTooltips2 {

    @SpireInsertPatch(locator = LocatorAfter.class, localvars = {"card", "t"})
    public static void InsertAfter(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard acard, @ByRef ArrayList<PowerTip>[] t) {
        List<TooltipInfo> tooltips = new ArrayList<>();
        if (FoilPatches.isFoil(acard)) {
            tooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("foil")), BaseMod.getKeywordDescription(makeID("foil"))));
        }
        if (!tooltips.isEmpty())
            t[0].addAll(tooltips.stream().map(TooltipInfo::toPowerTip).collect(Collectors.toList()));
    }

    private static class LocatorAfter extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "isEmpty");
            return LineFinder.findInOrder(ctMethodToPatch, (Matcher) methodCallMatcher);
        }
    }
}
