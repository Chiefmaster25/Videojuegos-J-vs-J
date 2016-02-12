package mx.itesm.whacamole;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {
    private Sprite sprite;
    public Fondo(Texture textura){
        sprite= new Sprite(textura);
        sprite.setAlpha(0.3f);
    }



    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

}
