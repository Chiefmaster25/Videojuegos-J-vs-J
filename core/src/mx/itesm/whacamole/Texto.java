package mx.itesm.whacamole;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by AllanIvan on 05/02/2016.
 */
public class Texto {
    private BitmapFont font;

    public Texto(){
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().scale(2);
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        GlyphLayout glyp = new GlyphLayout();
        glyp.setText(font, mensaje);
        float anchoTexto = glyp.width;
        font.draw(batch,glyp,x-anchoTexto/2,y);
    }
}
