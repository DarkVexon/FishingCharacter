package theFishing.achievements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theFishing.FishingMod;

import java.util.ArrayList;

public class FishingAchievementGrid {
    public ArrayList<FishingAchievementItem> items = new ArrayList<>();
    private static final float SPACING = 200.0F * Settings.scale;
    private static final int ITEMS_PER_ROW = 5;

    public FishingAchievementGrid() {
        FishingAchievementItem.atlas = new TextureAtlas(Gdx.files.internal("fishingResources/images/achievements/AdventurerAchievements.atlas"));
        loadAchievement("STARLIGHT", false);
        loadAchievement("OLD_TIMES", false);
        loadAchievement("ETERNITY", false);
        loadAchievement("DELVE_GRADUATE", false);
        loadAchievement("ADVENTURER_MASTERY", false);
    }

    private void loadAchievement(String id, boolean isHidden) {
        String fullId = FishingMod.makeID(id);
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(fullId);
        String name = uiStrings.TEXT[0];
        String description = uiStrings.TEXT[1];
        TextureAtlas.AtlasRegion AchievementImageUnlocked = FishingAchievementItem.atlas.findRegion("unlocked/" + id);
        TextureAtlas.AtlasRegion AchievementImageLocked = FishingAchievementItem.atlas.findRegion("locked/" + id);
        items.add(new FishingAchievementItem(name, description, fullId, isHidden, AchievementImageUnlocked, AchievementImageLocked));
    }

    public void updateAchievementStatus() {
        for (FishingAchievementItem item : items) {
            String achievementKey = item.getKey();
            boolean isUnlocked = UnlockTracker.isAchievementUnlocked(achievementKey);
            item.isUnlocked = isUnlocked;
            item.reloadImg();
        }
    }

    public void render(SpriteBatch sb, float renderY) {
        for (int i = 0; i < items.size(); ++i) {
            items.get(i).render(sb, 560.0F * Settings.scale + (i % ITEMS_PER_ROW) * SPACING, renderY - (i / ITEMS_PER_ROW) * SPACING + 680.0F * Settings.yScale);
        }
    }

    public float calculateHeight() {
        int numRows = (items.size() + ITEMS_PER_ROW - 1) / ITEMS_PER_ROW;
        return numRows * SPACING + 50.0F * Settings.scale;
    }

    public void update() {
        updateAchievementStatus();
        for (FishingAchievementItem item : items) {
            item.update();
        }
    }

}