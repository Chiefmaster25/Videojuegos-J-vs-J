package mx.itesm.whacamole;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BotonMenu {

    private Sprite sprite;
    public BotonMenu(Texture textura){
        sprite= new Sprite(textura);
        sprite.setAlpha(0.6f);
    }



    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosicion(float x, float y) {
        sprite.setPosition(200, 200);
    }


    public Sprite getSprite() {
        return sprite;
    }
}