package theFishing.patch.foil;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.shop.ShopScreen;
import theFishing.TheFishing;
import theFishing.cards.AbstractFishingCard;
import theFishing.util.Wiz;

import java.util.ArrayList;

import static theFishing.util.Shaders.fragmentShaderHSLC;
import static theFishing.util.Shaders.vertexShaderHSLC;

public class FoilPatches {

    // GAMEPLAY STUFF

    public static final float FOIL_CHANCE_REWARDS = 3F; // 1 out of X rewards
    public static final int SHOP_FOIL_CARDS = 2; // X out of the shop's cards
    public static final float SHOP_FOIL_MARKUP = 1.1F; // how much more expensive foils should be

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class FoilField {
        public static SpireField<Boolean> foil = new SpireField<>(() -> false);
    }

    public static boolean isFoil(AbstractCard card) {
        return FoilField.foil.get(card);
    }

    public static void makeFoil(AbstractCard card) {
        FoilField.foil.set(card, true);
        if (card.color == TheFishing.Enums.FISHING_COLOR && card instanceof AbstractFishingCard) {
            switch (card.type) {
                case ATTACK:
                case SKILL:
                case POWER:
                    ((AbstractFishingCard) card).setBackgroundTexture("fishingResources/images/512/" + card.type.toString().toLowerCase() + "_foil.png", "fishingResources/images/1024/" + card.type.toString().toLowerCase() + "_foil.png");
                    break;
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class FoilCopies {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            if (isFoil(__instance)) {
                makeFoil(__result);
            }

            return __result;
        }
    }

    @SpirePatch2(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class FoilInRewards {
        public static void Postfix(ArrayList<AbstractCard> __result) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == TheFishing.Enums.THE_FISHING && AbstractDungeon.cardRng.random() <= (1 / FOIL_CHANCE_REWARDS)) {
                makeFoil(Wiz.getRandomItem(__result, AbstractDungeon.cardRng));
            }
        }
    }

    @SpirePatch(
            clz = ShopScreen.class,
            method = "initCards"
    )
    public static class FoilInShops {
        public static void Postfix(ShopScreen __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == TheFishing.Enums.THE_FISHING) {
                ArrayList<AbstractCard> shopCards = new ArrayList<>();
                ArrayList<AbstractCard> coloredCards = ReflectionHacks.getPrivate(__instance, ShopScreen.class, "coloredCards");
                ArrayList<AbstractCard> colorlessCards = ReflectionHacks.getPrivate(__instance, ShopScreen.class, "colorlessCards");
                shopCards.addAll(coloredCards);
                shopCards.addAll(colorlessCards);
                for (int i = 0; i < SHOP_FOIL_CARDS; i++) {
                    AbstractCard target = shopCards.remove(AbstractDungeon.cardRng.random(shopCards.size() - 1));
                    makeFoil(target);
                    target.price *= SHOP_FOIL_MARKUP;
                }
            }
        }
    }

    // VISUAL STUFF


    private static final ShaderProgram shade = new ShaderProgram(vertexShaderHSLC, fragmentShaderHSLC);
    private static final Color hslcBackground = new Color(0.5F, 0.6F, 0.7F, 0.55F);
    private static final Color hslcArt = new Color(0.66F, 0.6F, 0.5F, 0.6F);
    private static final Color hslcCardBacks = new Color(0.66F, 0.5F, 0.575F, 0.5F);

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderCardBg"
    )
    public static class FoilShiny {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcBackground);
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR) {
                sb.setShader(oldShader);
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPortrait"
    )
    public static class FoilSpecialArt {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcArt);
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderCardBack"
    )
    public static class FoilShinySingleCardView {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = sb.getColor();
                sb.setColor(hslcBackground);
            }
        }

        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR) {
                sb.setShader(oldShader);
                sb.setColor(oldColor);
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderPortrait"
    )
    public static class FoilSpecialArtSingleCardView {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card)) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = sb.getColor();
                sb.setColor(hslcArt);
            }
        }

        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card)) {
                sb.setShader(oldShader);
                sb.setColor(oldColor);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderBack"
    )
    public static class FoilCardsSpecialCardbacks {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static void Prefix(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(shade);
                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcCardBacks);
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
                sb.setColor(oldColor);
            }
        }
    }


}
