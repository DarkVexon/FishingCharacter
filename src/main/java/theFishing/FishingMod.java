package theFishing;

import basemod.*;
import basemod.abstracts.CustomSavable;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import basemod.patches.com.megacrit.cardcrawl.screens.options.DropdownMenu.DropdownColoring;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.widepotions.WidePotionsMod;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theFishing.actions.EnterTheDungeonAction;
import theFishing.boards.AbstractBoard;
import theFishing.boards.TopPanelBoard;
import theFishing.boards.dailies.LandOfGiants;
import theFishing.cards.AbstractFishingCard;
import theFishing.cards.cardvars.SecondDamage;
import theFishing.cards.cardvars.SecondMagicNumber;
import theFishing.cards.cardvars.ThirdMagicNumber;
import theFishing.patch.PreDrawPatch;
import theFishing.patch.foil.FoilPatches;
import theFishing.potions.CarePackage;
import theFishing.potions.OceanInAJar;
import theFishing.potions.StarlightSoda;
import theFishing.quest.QuestHelper;
import theFishing.relics.AbstractAdventurerRelic;
import theFishing.util.FishingAchievementUnlocker;
import theFishing.util.FoilSparkleHandler;
import theFishing.util.Wiz;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class FishingMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, PostDeathSubscriber, EditCharactersSubscriber, PostBattleSubscriber, OnStartBattleSubscriber, PostPlayerUpdateSubscriber, AddAudioSubscriber, PostInitializeSubscriber, PostUpdateSubscriber, StartGameSubscriber {

    public static final String modID = "fishing";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static Color characterColor = Color.valueOf("4fd1db");

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse2.png";
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
    public static SpireConfig config;
    public static boolean foilAnywhere;
    public static int delvePreference;
    private static String completedDelveBonuses = "";

    @SpireEnum
    public static AbstractCard.CardTags DELVES;

    public FishingMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheFishing.Enums.FISHING_COLOR, characterColor, characterColor, characterColor, characterColor, characterColor, characterColor, characterColor, ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S, ATTACK_L_ART, SKILL_L_ART, POWER_L_ART, CARD_ENERGY_L, TEXT_ENERGY);
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

    public static void initialize() throws IOException {
        Properties defaults = new Properties();
        defaults.setProperty("foilanywhere", "false");
        defaults.setProperty("delvepreference", "0");
        defaults.setProperty("completedDelveBonuses", "");
        config = new SpireConfig(modID, "config", defaults);
        foilAnywhere = config.getBool("foilanywhere");
        delvePreference = config.getInt("delvepreference");
        completedDelveBonuses = config.getString("completedDelveBonuses");

        fishingMod = new FishingMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheFishing(TheFishing.characterStrings.NAMES[1], TheFishing.Enums.THE_FISHING), CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheFishing.Enums.THE_FISHING);

        BaseMod.addPotion(OceanInAJar.class, Color.BLUE.cpy(), Color.SKY.cpy(), null, OceanInAJar.POTION_ID, TheFishing.Enums.THE_FISHING);
        BaseMod.addPotion(StarlightSoda.class, Color.YELLOW.cpy(), Color.BLACK.cpy(), null, StarlightSoda.POTION_ID, TheFishing.Enums.THE_FISHING);
        BaseMod.addPotion(CarePackage.class, Color.CORAL.cpy(), Color.GRAY.cpy(), null, CarePackage.POTION_ID, TheFishing.Enums.THE_FISHING);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID).packageFilter(AbstractAdventurerRelic.class).any(AbstractAdventurerRelic.class, (info, relic) -> {
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
        BaseMod.addDynamicVariable(new ThirdMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID).packageFilter(AbstractFishingCard.class).setDefaultSeen(true).cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/" + getLangString() + "/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIStrings.json");

        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
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
        PreDrawPatch.DRAWN_DURING_TURN = false;
        EnterTheDungeonAction.timesDelvedThisCombat = 0;

        QuestHelper.reset();
        activeBoard.reset();

        if (AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING)) activeBoard.atBattleStartPreDraw();
    }

    @Override
    public void receivePostPlayerUpdate() {
        if (Wiz.isInCombat()) {
            if (!QuestHelper.quests.isEmpty()) {
                QuestHelper.update();
            }
            if (FishingMod.activeBoard != null && FishingMod.activeBoard.shouldBeActive()) {
                FishingMod.activeBoard.update();
            }
        }
    }

    public static boolean isThisVoyaged(AbstractCard card) {
        return (voyagedCards.contains(card));
    }

    @Override
    public void receivePostInitialize() {
        initializeSaveData();
        resetCompletedDelveBonuses();

        if (Loader.isModLoaded("rare-cards-sparkle")) {
            FoilSparkleHandler.init();
        }

        if (Loader.isModLoaded("widepotions")) {
            WidePotionsMod.whitelistSimplePotion(OceanInAJar.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(StarlightSoda.POTION_ID);
            WidePotionsMod.whitelistSimplePotion(CarePackage.POTION_ID);
        }

        BaseMod.addTopPanelItem(new TopPanelBoard());


        String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("ConfigMenu")).TEXT;
        ModPanel settingsPanel = new ModPanel();

        settingsPanel.addUIElement(new ModLabeledToggleButton(TEXT[3], 365, 700, Settings.CREAM_COLOR, FontHelper.charDescFont, config.getBool("foilanywhere"), settingsPanel, label -> {
        }, button -> {
            foilAnywhere = button.enabled;
            config.setBool("foilanywhere", button.enabled);
            try {
                config.save();
            } catch (Exception e) {
            }
        }));

        settingsPanel.addUIElement(new ModLabel("Delve Preference", 365, 650, settingsPanel, (modLabel -> {

        })));

// Assuming 'completedDelveBonuses' is accessible here, otherwise pass it as needed
        ArrayList<String> dropdownOptions = new ArrayList<>();
        dropdownOptions.add("Daily");
        dropdownOptions.add("Random");

        for (String ID : AbstractBoard.idsList) {
            dropdownOptions.add(AbstractBoard.getBoardByID(ID).name);
        }

        DropdownMenu d = new DropdownMenu((dropdownMenu, i, s) -> {
            delvePreference = i;
            config.setInt("delvepreference", delvePreference);
            try {
                config.save();
            } catch (Exception e) {
                // Handle exception
            }
        }, dropdownOptions, FontHelper.tipBodyFont, Settings.CREAM_COLOR, dropdownOptions.size());

        DropdownColoring.RowToColor.function.set(d, (index) -> {
            // Assuming Daily and Random are the first two items and should remain default color
            if (index > 1) {
                String boardId = AbstractBoard.idsList.get(index - 2); // Adjust index for "Daily" and "Random"
                if (completedDelveBonuses.contains(boardId)) {
                    return Settings.GREEN_TEXT_COLOR; // Change to green if completed
                }
            }
            return null; // Default color otherwise
        });

        settingsPanel.addUIElement(new IUIElement() {
            @Override
            public void render(SpriteBatch spriteBatch) {
                d.render(spriteBatch, 365 * Settings.scale, 625 * Settings.scale);
            }

            @Override
            public void update() {
                d.update();
            }

            @Override
            public int renderLayer() {
                return 0;
            }

            @Override
            public int updateOrder() {
                return 0;
            }
        });

        BaseMod.registerModBadge(new Texture(makeImagePath("ui/badge.png")), TEXT[0], TEXT[1], TEXT[2], settingsPanel);
    }

    public static float time = 0f;

    @Override
    public void receivePostUpdate() {
        time += Gdx.graphics.getDeltaTime();
    }

    public static Settings.GameLanguage[] SupportedLanguages = {Settings.GameLanguage.ENG, Settings.GameLanguage.KOR};

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public static AbstractBoard activeBoard = null;
    public static boolean delvedThisTurn = false;

    private void initializeSaveData() {
        BaseMod.addSaveField("FishingFoilCards", new CustomSavable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> onSave() {
                ArrayList<Integer> foilIndices = new ArrayList<>();
                for (int i = 0; i < AbstractDungeon.player.masterDeck.size(); i++) {
                    if (FoilPatches.isFoil(AbstractDungeon.player.masterDeck.group.get(i))) {
                        foilIndices.add(i);
                    }
                }
                return foilIndices;
            }

            @Override
            public void onLoad(ArrayList<Integer> integers) {
                if (integers != null) for (Integer i : integers) {
                    if (AbstractDungeon.player.masterDeck.size() > i) {
                        FoilPatches.makeFoil(AbstractDungeon.player.masterDeck.group.get(i));
                    }
                }
            }
        });

        BaseMod.addSaveField("FishingRunCurrentBoard", new CustomSavable<String>() {
            @Override
            public String onSave() {
                return activeBoard.id;
            }

            @Override
            public void onLoad(String s) {
                if (s != null) activeBoard = AbstractBoard.getBoardByID(s);
                else activeBoard = AbstractBoard.getRunBoard();

                reInitializeDelveKeyword();

                if (activeBoard.id.equalsIgnoreCase(LandOfGiants.ID)) {
                    for (AbstractCard q : AbstractDungeon.player.masterDeck.group) {
                        if (q.hasTag(FishingMod.DELVES)) {
                            AbstractCard source = CardLibrary.getCard(q.cardID);
                            q.cost = source.cost + 1;
                            q.costForTurn = q.cost;
                        }
                    }
                }
            }
        });
    }

    public void receivePostDeath() {
        if (FishingMod.activeBoard.id != null && !completedDelveBonuses.contains(FishingMod.activeBoard.id)) {
            completedDelveBonuses += (completedDelveBonuses.isEmpty() ? "" : ",") + FishingMod.activeBoard.id;

            // Update the SpireConfig
            config.setString("completedDelveBonuses", completedDelveBonuses);
            try {
                config.save();
            } catch (Exception e) {
                // Handle exception
            }
        }

        // Check if all current Delve Bonuses have been completed
        String[] completedBonuses = completedDelveBonuses.split(",");
        if (AbstractBoard.idsList.size() == completedBonuses.length) {
            boolean allDelveBonusesCompleted = true;
            for (String id : AbstractBoard.idsList) {
                if (!Arrays.asList(completedBonuses).contains(id)) {
                    allDelveBonusesCompleted = false;
                    break;
                }
            }

            if (allDelveBonusesCompleted) {
                FishingAchievementUnlocker.unlockAchievement(FishingMod.makeID("DELVE_GRADUATE"));
            }
        }
    }

    private static String[] names = {
            "delve",
            "delved"
    };

    public static void reInitializeDelveKeyword() {
        System.out.println("Re-Initializing Delve Keyword. Active Board: " + activeBoard);
        if (activeBoard != null) {
            GameDictionary.keywords.put("fishing:delve", "Trigger today's Delve effect: NL " + activeBoard.getLocString(activeBoard.id).TEXT_DICT.get("F1"));
            GameDictionary.keywords.put("fishing:delved", "Trigger today's Delve effect: NL " + activeBoard.getLocString(activeBoard.id).TEXT_DICT.get("F1"));
        } else {
            GameDictionary.keywords.put("fishing:delve", "Trigger today's Delve effect.");
            GameDictionary.keywords.put("fishing:delved", "Trigger today's Delve effect.");
        }
    }

    @Override
    public void receiveStartGame() {
        if (!CardCrawlGame.loadingSave) {
            activeBoard = AbstractBoard.getRunBoard();
            reInitializeDelveKeyword();
            if (AbstractDungeon.player.chosenClass.equals(TheFishing.Enums.THE_FISHING)) {
                activeBoard.atRunStart();
            }
        }
    }

    public void resetCompletedDelveBonuses() {
        completedDelveBonuses = "";  // Clear the content of the string
        config.setString("completedDelveBonuses", completedDelveBonuses);  // Update the config to reflect the empty string
        try {
            config.save();  // Save the configuration
            BaseMod.logger.info("Completed Delve Bonuses have been reset.");
        } catch (Exception e) {
            BaseMod.logger.error("Error saving config after resetting Completed Delve Bonuses", e);
        }
    }


    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(makeID("WAKA_WAKA"), "fishingResources/audio/eat_ghost.ogg");
        BaseMod.addAudio(makeID("EAT_FRUIT"), "fishingResources/audio/eatfruit.ogg");
        BaseMod.addAudio(makeID("CLOCKTOWER"), "fishingResources/audio/mm_clocktower_bell.mp3");
    }
}
