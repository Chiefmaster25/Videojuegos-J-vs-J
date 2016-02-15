package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

    //Boton regreso a menu
    private BotonCredito1 btnCred1;
    private Texture TexturaBtnCred1;


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


        btnCred1 = new BotonCredito1(TexturaBtnCred1);
        btnCred1.setPosicion(50,150);
    }



    private void cargarTexturas(){
        texturaFondo = new Texture(Gdx.files.internal("Destiny Logo.jpg"));
        TexturaBtnCred1 = new Texture(Gdx.files.internal("play.jpg"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        fondo.render(batch);
        btnCred1.render(batch);
        batch.end();

        leerRegreso();
    }

    private void leerRegreso() {
        if (Gdx.input.justTouched()) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); //Traduce las coordenadas
            float x = coordenadas.x;
            float y = coordenadas.y;
            if (verificarRegreso(x, y) == true){
                Gdx.app.log("leerEntrada","Tap sobre el botón");
                principal.setScreen(new PantallaMenu(principal));
                //Gdx.app.exit();
            }
        }
    }

    private boolean verificarRegreso(float x, float y) {
        Sprite sprite = btnCred1.getSprite();
        return x>=sprite.getX() && x<=sprite.getY() + sprite.getWidth() && y>= sprite.getY() && y<=sprite.getY()+sprite.getHeight();
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

