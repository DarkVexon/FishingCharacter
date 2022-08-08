package theFishing.patch.foil;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.TipHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static theFishing.FishingMod.makeID;

@SpirePatch(clz = TipHelper.class, method = "renderKeywords")
public class FoilTooltips1 {
    private static float BODY_TEXT_WIDTH = 0.0F;

    private static float TIP_DESC_LINE_SPACING = 0.0F;

    private static float BOX_EDGE_H = 0.0F;

    public static void Prefix(float x, @ByRef float[] y, SpriteBatch sb, ArrayList<String> keywords, AbstractCard ___card) {
        if (BODY_TEXT_WIDTH == 0.0F)
            getConstants();
        float sumTooltipHeight = 0.0F;
        for (String s : keywords) {
            if (GameDictionary.keywords.containsKey(s)) {
                float textHeight = -FontHelper.getSmartHeight(FontHelper.tipHeaderFont, TipHelper.capitalize(s), BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - FontHelper.getSmartHeight(FontHelper.tipBodyFont, (String) GameDictionary.keywords

                        .get(s), BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
                sumTooltipHeight -= textHeight + BOX_EDGE_H * 3.15F;
            }
        }
        List<TooltipInfo> tooltips = new ArrayList<>();
        if (FoilPatches.isFoil(___card) && !___card.rawDescription.contains("fishing:foil")) {
            tooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("foil")), BaseMod.getKeywordDescription(makeID("foil"))));
        }
        if (!tooltips.isEmpty())
            for (TooltipInfo tooltip : tooltips) {
                float textHeight = -FontHelper.getSmartHeight(FontHelper.tipHeaderFont, TipHelper.capitalize(tooltip.title), BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - FontHelper.getSmartHeight(FontHelper.tipBodyFont, tooltip.description, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
                textHeight -= sumTooltipHeight -= textHeight + BOX_EDGE_H * 3.15F;
            }
        sumTooltipHeight *= -1.0F;
        if (sumTooltipHeight > AbstractCard.IMG_HEIGHT)
            y[0] = y[0] + sumTooltipHeight - AbstractCard.IMG_HEIGHT;
    }

    public static void Postfix(float x, float y, SpriteBatch sb, ArrayList<String> keywords) {
        if (BODY_TEXT_WIDTH == 0.0F)
            getConstants();
        try {
            Field cardField = TipHelper.class.getDeclaredField("card");
            cardField.setAccessible(true);
            AbstractCard acard = (AbstractCard) cardField.get(null);
            List<TooltipInfo> tooltips = new ArrayList<>();
            if (FoilPatches.isFoil(acard)) {
                tooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("foil")), BaseMod.getKeywordDescription(makeID("foil"))));
            }
            if (!tooltips.isEmpty())
                for (TooltipInfo tooltip : tooltips) {
                    Field textHeight = TipHelper.class.getDeclaredField("textHeight");
                    textHeight.setAccessible(true);
                    float h = -FontHelper.getSmartHeight(FontHelper.tipHeaderFont, TipHelper.capitalize(tooltip.title), BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - FontHelper.getSmartHeight(FontHelper.tipBodyFont, tooltip.description, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING) - 7.0F * Settings.scale;
                    textHeight.set(null, Float.valueOf(h));
                    Method renderTipBox = TipHelper.class.getDeclaredMethod("renderTipBox", new Class[]{float.class, float.class, SpriteBatch.class, String.class, String.class});
                    renderTipBox.setAccessible(true);
                    renderTipBox.invoke(null, new Object[]{Float.valueOf(x), Float.valueOf(y), sb, tooltip.title, tooltip.description});
                    y -= h + BOX_EDGE_H * 3.15F;
                }
        } catch (NoSuchMethodException | IllegalAccessException | java.lang.reflect.InvocationTargetException |
                 NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void getConstants() {
        try {
            Field f = TipHelper.class.getDeclaredField("BODY_TEXT_WIDTH");
            f.setAccessible(true);
            BODY_TEXT_WIDTH = f.getFloat(null);
            f = TipHelper.class.getDeclaredField("TIP_DESC_LINE_SPACING");
            f.setAccessible(true);
            TIP_DESC_LINE_SPACING = f.getFloat(null);
            f = TipHelper.class.getDeclaredField("BOX_EDGE_H");
            f.setAccessible(true);
            BOX_EDGE_H = f.getFloat(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
