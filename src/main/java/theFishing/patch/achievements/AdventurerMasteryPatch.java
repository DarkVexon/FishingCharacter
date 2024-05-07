package theFishing.patch.achievements;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.VictoryScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theFishing.FishingMod;
import theFishing.TheFishing;

@SpirePatch(clz = VictoryScreen.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {MonsterGroup.class})
public class AdventurerMasteryPatch {
    public AdventurerMasteryPatch() {
    }

    @SpirePostfixPatch
    public static void Postfix(VictoryScreen __instance, MonsterGroup m) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p instanceof TheFishing && AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.actNum == 4) {
            UnlockTracker.unlockAchievement(FishingMod.makeID("ADVENTURER_MASTERY"));
        }
    }
}