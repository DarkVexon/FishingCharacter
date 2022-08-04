package theFishing.util;

import com.badlogic.gdx.graphics.Color;
import com.blanktheevil.rarecardssparkle.RareCardsSparkle;
import com.blanktheevil.rarecardssparkle.SparkleTimer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import theFishing.patch.foil.FoilPatches;

import static theFishing.FishingMod.makeID;

public class FoilSparkleHandler {
    public static void init() {
        RareCardsSparkle.addSparkleRule(
                makeID("FoilSparkle"), // some unique string usually prepended with modid:
                "Foil Sparkles", // this text is rendered in the mod settings menu
                new Color(0f, 1f, 0.85f, 0f), // this is the color of your sparkles.
                ImageMaster.GLOW_SPARK_2, // this is an AtlasRegion.
                true, // this is a boolean that allows the particles to have a random velocity upon initialization.
                new SparkleTimer(0.1f, 0.15f), // this is the minimum and maximum time between spawns in seconds.
                card -> FoilPatches.isFoil(card) // this is some Predicate that defines a boolean that must be true for a card to recieve these particles
        );
    }
}
