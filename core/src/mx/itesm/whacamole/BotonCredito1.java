package mx.itesm.whacamole;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by AllanIvan on 14/02/2016.
 */

    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.Sprite;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BotonCredito1 {

        private Sprite sprite;
        public BotonCredito1(Texture textura){
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

