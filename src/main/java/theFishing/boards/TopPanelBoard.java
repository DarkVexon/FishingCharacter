package theFishing.boards;

import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import theFishing.FishingMod;
import theFishing.TheFishing;
import theFishing.util.TexLoader;
import theFishing.util.Wiz;

public class TopPanelBoard extends TopPanelItem {

    private static final float tipYpos = Settings.HEIGHT - (120.0f * Settings.scale);

    public static final String ID = FishingMod.makeID("BoardInfo");

    public static final Texture ICON = TexLoader.getTexture("fishingResources/images/ui/candle_ooc.png");
    public static final Texture ICON_ST1 = TexLoader.getTexture("fishingResources/images/ui/candle3.png");
    public static final Texture ICON_ST2 = TexLoader.getTexture("fishingResources/images/ui/candle2.png");
    public static final Texture ICON_ST3 = TexLoader.getTexture("fishingResources/images/ui/candle1.png");
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    public TopPanelBoard() {
        super(ICON, ID);
    }

    @Override
    public boolean isClickable() {
        return false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player.chosenClass == TheFishing.Enums.THE_FISHING) { //TODO: Or if you have a Delve card
            if (Wiz.isInCombat()) {
                switch (FishingMod.activeBoard.progress) {
                    case 0:
                        image = ICON_ST1;
                        break;
                    case 1:
                        image = ICON_ST2;
                        break;
                    case 2:
                        image = ICON_ST3;
                        break;
                }
            } else {
                image = ICON;
            }
            render(sb, Color.WHITE);
            if (Wiz.isInCombat()) {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelAmountFont, Integer.toString(FishingMod.activeBoard.progress + 1), this.x + 58.0F * Settings.scale, this.y + 25.0F * Settings.scale, Color.WHITE.cpy());
            }
            if (getHitbox().hovered) {
                if (Wiz.isInCombat()) {
                    TipHelper.renderGenericTip(getHitbox().x, tipYpos, uiStrings.TEXT[0], uiStrings.TEXT[1] + FishingMod.activeBoard.getDescription() + uiStrings.TEXT[2] + (FishingMod.activeBoard.progress + 1) + LocalizedStrings.PERIOD);
                } else {
                    TipHelper.renderGenericTip(getHitbox().x, tipYpos, uiStrings.TEXT[0], uiStrings.TEXT[1] + FishingMod.activeBoard.getDescription());
                }
            }
            //else if (Wiz.isInCombat() && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode) && AbstractDungeon.player.hoveredCard != null && AbstractDungeon.player.hoveredCard.hasTag(FishingMod.DELVES)) {
            //    TipHelper.renderGenericTip(getHitbox().x, tipYpos, uiStrings.TEXT[3], FishingMod.activeBoard.getEffectDescription(FishingMod.activeBoard.progress % FishingMod.activeBoard.effects.size()));
            //}
        }
    }

    @Override
    protected void onClick() {

    }
}
