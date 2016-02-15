package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by AllanIvan on 14/02/2016.
 */
public class PantallaCreditos implements Screen {
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;
    private Fondo fondo;
    private Texture texturaFondo;

    public PantallaCreditos(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void show() {
        //crear la cámara y la vista
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();

        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        cargarTexturas();
        fondo = new Fondo(texturaFondo);

        batch = new SpriteBatch();
    }



    private void cargarTexturas(){
        texturaFondo = new Texture(Gdx.files.internal("Destiny Logo.jpg"));
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //texturaFondo.dispose();
    }

}

