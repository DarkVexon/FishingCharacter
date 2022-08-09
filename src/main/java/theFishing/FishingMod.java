package theFishing;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.cardvars.FishInCombatVar;
import theFishing.cards.cardvars.SecondDamage;
import theFishing.cards.cardvars.SecondMagicNumber;
import theFishing.patch.PreDrawPatch;
import theFishing.patch.foil.FoilPatches;
import theFishing.potions.CarePackage;
import theFishing.potions.OceanInAJar;
import theFishing.potions.StarlightSoda;
import theFishing.quest.QuestHelper;
import theFishing.relics.AbstractEasyRelic;
import theFishing.util.FoilSparkleHandler;
import theFishing.util.Wiz;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class FishingMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostBattleSubscriber,
        OnStartBattleSubscriber,
        CustomSavable<ArrayList<Boolean>>,
        PostPlayerUpdateSubscriber,
        AddAudioSubscriber,
        PostInitializeSubscriber,
        PostUpdateSubscriber {

    public static final String modID = "fishing";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = Color.valueOf("4fd1db");

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public static ArrayList<AbstractCard> voyagedCards = new ArrayList<>();

    private static FishingMod fishingMod;

    public FishingMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheFishing.Enums.FISHING_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        fishingMod = new FishingMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheFishing(TheFishing.characterStrings.NAMES[1], TheFishing.Enums.THE_FISHING),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheFishing.Enums.THE_FISHING);

        BaseMod.addPotion(OceanInAJar.class, Color.BLUE.cpy(), Color.SKY.cpy(), null, OceanInAJar.POTION_ID, TheFishing.Enums.THE_FISHING);
        BaseMod.addPotion(StarlightSoda.class, Color.YELLOW.cpy(), Color.BLACK.cpy(), null, StarlightSoda.POTION_ID, TheFishing.Enums.THE_FISHING);
        BaseMod.addPotion(CarePackage.class, Color.CORAL.cpy(), Color.GRAY.cpy(), null, CarePackage.POTION_ID, TheFishing.Enums.THE_FISHING);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        BaseMod.addDynamicVariable(new FishInCombatVar());
        new AutoAdd(modID)
                .packageFilter(AbstractFishingCard.class)
                .setDefaultSeen(false)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        voyagedCards.clear();
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        QuestHelper.reset();
        PreDrawPatch.DRAWN_DURING_TURN = false;
    }

    @Override
    public void receivePostPlayerUpdate() {
        if (Wiz.isInCombat() && !QuestHelper.quests.isEmpty()) {
            QuestHelper.update();
        }
    }

    @Override
    public ArrayList<Boolean> onSave() {
        ArrayList<Boolean> foilCards = new ArrayList<>();
        for (AbstractCard q : AbstractDungeon.player.masterDeck.group) {
            foilCards.add(FoilPatches.isFoil(q));
        }
        return foilCards;
    }

    @Override
    public void onLoad(ArrayList<Boolean> foilCards) {
        if (foilCards != null)
            for (int i = 0; i < foilCards.size(); i++) {
                if (foilCards.get(i)) {
                    if (AbstractDungeon.player.masterDeck.size() > i)
                        FoilPatches.makeFoil(AbstractDungeon.player.masterDeck.group.get(i));
                }
            }
    }

    public static boolean isThisVoyaged(AbstractCard card) {
        return (voyagedCards.contains(card));
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(makeID("WAKA_WAKA"), "fishingResources/audio/eat_ghost.ogg");
        BaseMod.addAudio(makeID("EAT_FRUIT"), "fishingResources/audio/eatfruit.ogg");
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addSaveField("FishingMod", fishingMod);

        if (Loader.isModLoaded("rare-cards-sparkle")) {
            FoilSparkleHandler.init();
        }
    }

    public static float time = 0f;

    @Override
    public void receivePostUpdate() {
        time += Gdx.graphics.getDeltaTime();
    }

    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }
}
