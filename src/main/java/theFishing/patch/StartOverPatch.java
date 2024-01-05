package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import theFishing.FishingMod;

public class StartOverPatch {
    @SpirePatch(
            clz = MainMenuScreen.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {}
    )
    public static class OnRemoveDefendDoAThing {
        public static void Postfix() {
            FishingMod.activeBoard = null;
            FishingMod.reInitializeDelveKeyword();
        }
    }
}
