/*
package theFishing.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static theFishing.FishingMod.makeID;

public class MagicTheGatheringCard extends AbstractFishingCard {
    private Texture myImg;
    private byte[] imageData;
    private boolean loaded = false;
    public final static String ID = makeID("MagicTheGathering");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 2

    public MagicTheGatheringCard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        if (CardCrawlGame.isInARun())
            setupMTG();
    }

    private void setupMTG() {
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method("GET").url("https://api.scryfall.com/cards/random?format=image&version=normal").header("Content-Type", "application/json").header("Accept", "application/json").header("User-Agent", "curl/7.43.0").build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                try {
                    BufferedImage image;
                    image = ImageIO.read(httpResponse.getResultAsStream());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "jpeg", baos);
                    imageData = baos.toByteArray();
                    loaded = true;
                } catch (Exception e) {
                    System.out.println("oopsie doopsie");
                    e.printStackTrace();
                }
            }

            public void failed(Throwable t) {
                System.out.println(t.getMessage());
            }

            public void cancelled() {
                System.out.println("nopesie");
            }
        });
    }

    @Override
    public void update() {
        super.update();
        if (loaded) {
            myImg = new Texture(new Pixmap(imageData, 0, imageData.length));
            loaded = false;
        }
    }

    private static final float scaleChange = 0.8F;

    @Override
    public void render(SpriteBatch sb) {
        if (myImg != null) {
            sb.setColor(Color.WHITE.cpy());
            sb.draw(myImg, this.current_x - ((myImg.getWidth() * drawScale * scaleChange) / 2), this.current_y - ((myImg.getHeight() * drawScale * scaleChange) / 2), 0, 0, myImg.getWidth() * drawScale * scaleChange, myImg.getHeight() * drawScale * scaleChange, 1, 1, this.angle, 0, 0, myImg.getWidth(), myImg.getHeight(), false, false);
        } else {
            super.render(sb);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {

    }
}
*/
