package theFishing.patch;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import theFishing.FishingMod;
import theFishing.boards.dailies.Termina;


@SpirePatch(clz = DiscardAtEndOfTurnAction.class, method = "update")
public class SpecialRetainPatch {
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(FieldAccess f) throws CannotCompileException {
                if (f.getFieldName().equals("selfRetain")) {
                    f.replace("$_ = " + SpecialRetainPatch.class.getName() + ".Do($0, $proceed($$));");
                }
            }
        };
    }

    @SuppressWarnings("unused")
    public static boolean Do(AbstractCard card, boolean orig) {
        //return true;
        if (FishingMod.activeBoard != null && FishingMod.activeBoard.id.equals(Termina.ID) && card.hasTag(FishingMod.DELVES)) {
            return true;
        }
        return orig;
    }
}