//package theFishing.cards;
//
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import theFishing.util.ImageHelper;
//import theFishing.util.TexLoader;
//
//import static theFishing.FishingMod.makeImagePath;
//
//public interface MasterDeckButtonCard {
//    TextureAtlas.AtlasRegion defaultButton = ImageHelper.asAtlasRegion(TexLoader.getTexture(makeImagePath("512/default.png")));
//
//    default boolean showButton() {
//        return true;
//    }
//
//    void onButtonPressed();
//
//    default TextureAtlas.AtlasRegion buttonArt() {
//        return defaultButton;
//    }
//}
