package mx.itesm.whacamole;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by AllanIvan on 12/02/2016.
 */
public class BotonMenu3 {

    private Sprite sprite;
    public BotonMenu3(Texture textura){
        sprite = new Sprite(textura);
        sprite.setAlpha(0.6f);
    }


    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosicion(float x, float y) {
        sprite.setPosition(x, y);
    }

    public Sprite getSprite() {
        return sprite;
    }
}