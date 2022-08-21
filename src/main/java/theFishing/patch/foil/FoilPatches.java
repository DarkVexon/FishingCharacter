package theFishing.patch.foil;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.shop.ShopScreen;
import theFishing.TheFishing;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.EndsOfTheEarth;
import theFishing.effects.CollectorReminderEffect;
import theFishing.powers.CollectorPower;
import theFishing.relics.Newsletter;
import theFishing.util.ImageHelper;
import theFishing.util.TexLoader;
import theFishing.util.Wiz;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import static theFishing.FishingMod.makeImagePath;

public class FoilPatches {

    // GAMEPLAY STUFF

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
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == TheFishing.Enums.THE_FISHING) {
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
                if (AbstractDungeon.player.hasRelic(Newsletter.ID)) {
                    for (AbstractCard q : shopCards) {
                        makeFoil(q);
                        q.upgrade();
                        q.price *= Newsletter.SHOP_CARD_PRICE_REDUCE;
                    }
                } else {
                    for (int i = 0; i < SHOP_FOIL_CARDS; i++) {
                        AbstractCard target = shopCards.remove(AbstractDungeon.cardRng.random(shopCards.size() - 1));
                        makeFoil(target);
                        target.price *= SHOP_FOIL_MARKUP;
                    }
                }
            }
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "getColorlessRewardCards"
    )
    public static class NeowFoils1 {
        public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result, NeowReward __instance, boolean rareOnly) {
            if (AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING))
                makeFoil(Wiz.getRandomItem(__result, AbstractDungeon.cardRandomRng));
            return __result;
        }
    }

    @SpirePatch(
            clz = NeowReward.class,
            method = "getRewardCards"
    )
    public static class NeowFoils2 {
        public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result, NeowReward __instance, boolean rareOnly) {
            if (AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING))
                makeFoil(Wiz.getRandomItem(__result, AbstractDungeon.cardRandomRng));
            return __result;
        }
    }

    // VISUAL STUFF

//    @SpirePatch(
//            clz = AbstractCard.class,
//            method = "renderCardBg"
//    )
//    public static class FoilShiny {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
//            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR && __instance.color != AbstractCard.CardColor.COLORLESS) {
//                oldShader = sb.getShader();
//                sb.setShader(shade);
//                oldColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
//                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", hslcBackground);
//            }
//        }
//
//        public static void Postfix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
//            if (isFoil(__instance) && __instance.color != TheFishing.Enums.FISHING_COLOR && __instance.color != AbstractCard.CardColor.COLORLESS) {
//                sb.setShader(oldShader);
//                ReflectionHacks.setPrivate(__instance, AbstractCard.class, "renderColor", oldColor);
//            }
//        }
//    }

    public static final ShaderProgram ART_SHADER = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("fishingResources/shaders/foil_card_art.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPortrait"
    )
    public static class FoilSpecialArt {
        private static ShaderProgram oldShader;
        private static Color oldColor;

        public static HashMap<String, Float> shiftAmts = new HashMap<>();

        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                oldShader = sb.getShader();
                sb.setShader(ART_SHADER);
                ART_SHADER.setUniformf("shift_amt", shiftAmts.computeIfAbsent(__instance.cardID, key -> {
                    Random rng = new Random((long) __instance.cardID.hashCode());
                    return 0.2F + rng.random(0F, 0.6F);
                }));
            }
        }

        public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
            if (isFoil(__instance)) {
                sb.setShader(oldShader);
            }
        }
    }

//    @SpirePatch(
//            clz = SingleCardViewPopup.class,
//            method = "renderCardBack"
//    )
//    public static class FoilShinySingleCardView {
//        private static ShaderProgram oldShader;
//        private static Color oldColor;
//
//        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR && card.color != AbstractCard.CardColor.COLORLESS) {
//                oldShader = sb.getShader();
//                sb.setShader(shade);
//                oldColor = sb.getColor();
//                sb.setColor(hslcBackground);
//            }
//        }
//
//        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
//            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
//            if (isFoil(card) && card.color != TheFishing.Enums.FISHING_COLOR && card.color != AbstractCard.CardColor.COLORLESS) {
//                sb.setShader(oldShader);
//                sb.setColor(oldColor);
//            }
//        }
//    }

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
                sb.setShader(ART_SHADER);
                ART_SHADER.setUniformf("shift_amt", FoilSpecialArt.shiftAmts.computeIfAbsent(card.cardID, key -> {
                    Random rng = new Random((long) card.cardID.hashCode());
                    return 0.2F + rng.random(0F, 0.6F);
                }));
            }
        }

        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card)) {
                sb.setShader(oldShader);
            }
        }
    }

    /*
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
     */

    private static void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, float drawScale, float angle) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, drawScale * Settings.scale, drawScale * Settings.scale, angle);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderAttackBg"
    )
    public static class FoilColorlessLooksCool1 {
        private static TextureAtlas.AtlasRegion colorlessFoilAttackAtlasRegion = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/colorless_attack_foil.png")));

        public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance) && __instance.color == AbstractCard.CardColor.COLORLESS) {
                Color renderColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                renderHelper(sb, renderColor, colorlessFoilAttackAtlasRegion, x, y, __instance.drawScale, __instance.angle);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderSkillBg"
    )
    public static class FoilColorlessLooksCool2 {
        private static TextureAtlas.AtlasRegion colorlessFoilSkillAtlasRegion = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/colorless_skill_foil.png")));

        public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance) && __instance.color == AbstractCard.CardColor.COLORLESS) {
                Color renderColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                renderHelper(sb, renderColor, colorlessFoilSkillAtlasRegion, x, y, __instance.drawScale, __instance.angle);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPowerBg"
    )
    public static class FoilColorlessLooksCool3 {
        private static TextureAtlas.AtlasRegion colorlessFoilPowerAtlasRegion = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/colorless_power_foil.png")));

        public static SpireReturn Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            if (isFoil(__instance) && __instance.color == AbstractCard.CardColor.COLORLESS) {
                Color renderColor = ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
                renderHelper(sb, renderColor, colorlessFoilPowerAtlasRegion, x, y, __instance.drawScale, __instance.angle);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "getCardBackAtlasRegion"
    )
    public static class FoilColorlessLooksCool4 {
        private static TextureAtlas.AtlasRegion colorlessFoilAttackAtlasRegion1024 = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("1024/colorless_attack_foil.png")));
        private static TextureAtlas.AtlasRegion colorlessFoilSkillAtlasRegion1024 = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("1024/colorless_skill_foil.png")));
        private static TextureAtlas.AtlasRegion colorlessFoilPowerAtlasRegion1024 = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("1024/colorless_power_foil.png")));

        public static SpireReturn Prefix(SingleCardViewPopup __instance) {
            AbstractCard card = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isFoil(card) && card.color == AbstractCard.CardColor.COLORLESS) {
                switch (card.type) {
                    case ATTACK:
                        return SpireReturn.Return(colorlessFoilAttackAtlasRegion1024);
                    case POWER:
                        return SpireReturn.Return(colorlessFoilPowerAtlasRegion1024);
                    case SKILL:
                    case STATUS:
                    case CURSE:
                        return SpireReturn.Return(colorlessFoilSkillAtlasRegion1024);
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class CollectorTimerField {
        public static final float TIME_BETWEEN_SPARKS = 0.075F;
        public static SpireField<Float> collectorTimer = new SpireField<>(() -> TIME_BETWEEN_SPARKS);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "update"
    )
    public static class CollectorReminderFlames {
        public static void Postfix(AbstractCard __instance) {
            if (Wiz.isInCombat() && (AbstractDungeon.player.hand.contains(__instance) || (__instance.cardID.equals(EndsOfTheEarth.ID) && AbstractDungeon.player.discardPile.contains(__instance))) && AbstractDungeon.player.hasPower(CollectorPower.ID) && !((CollectorPower) AbstractDungeon.player.getPower(CollectorPower.ID)).activated) {
                if (__instance.rarity == AbstractCard.CardRarity.RARE || isFoil(__instance)) {
                    float timer = CollectorTimerField.collectorTimer.get(__instance);
                    timer -= Gdx.graphics.getDeltaTime();
                    if (timer <= 0) {
                        AbstractDungeon.topLevelEffectsQueue.add(new CollectorReminderEffect(__instance));
                        CollectorTimerField.collectorTimer.set(__instance, CollectorTimerField.TIME_BETWEEN_SPARKS);
                    } else {
                        CollectorTimerField.collectorTimer.set(__instance, timer);
                    }
                }
            }
        }
    }
}
