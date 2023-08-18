package theFishing.patch;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import theFishing.TheFishing;
import theFishing.boards.AbstractBoard;
import theFishing.boards.TopPanelBoard;

@SpirePatch(clz = CharacterOption.class, method = "renderInfo")
public class CharacterSelectBoard {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString("fishing:CharacterOption").TEXT;
    private static final Color COLOR = new Color(0.55f, 1.00f, 0.26f, 1f);

    private static String curString = null;

    public static void Prefix(CharacterOption __instance, SpriteBatch sb) {
        if (__instance.c instanceof TheFishing) {
            if (curString == null) {
                curString = AbstractBoard.getRunBoard().name; //TODO: Won't update at midnight
            }
            sb.draw(TopPanelBoard.ICON, getF(__instance, "infoX") - 24f, getF(__instance, "infoY") - 125f * Settings.scale - 24f, 24f, 24f, 48f, 48f, Settings.scale, Settings.scale, 0f, 0, 0, 64, 64, false, false);
            FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, TEXT[0] + AbstractBoard.getRunBoard().name, getF(__instance, "infoX") + 25F * Settings.scale, getF(__instance, "infoY") - 125f * Settings.scale, 10000f, 10000f, COLOR);
        }
    }

    private static float getF(CharacterOption __instance, String fieldName) {
        return ReflectionHacks.getPrivate(__instance, CharacterOption.class, fieldName);
    }
}