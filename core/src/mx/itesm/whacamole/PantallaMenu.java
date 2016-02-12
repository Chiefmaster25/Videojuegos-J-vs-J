package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PantallaMenu implements Screen {

    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private SpriteBatch batch;

    //Fondo
    private Fondo fondo;
    private Texture texturaFondo;

    private BotonMenu btnPlay;
    private BotonMenu2 btnPlay2;
    private BotonMenu3 btnPlay3;
    private Texture texturaBtnPlay;
    private Texture texturaBtnPlay2;
    private Texture texturaBtnPlay3;


    public PantallaMenu(Principal principal) {
        this.principal=principal;
    }

    @Override
    public void show() {
        //crear la cámara y la vista
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();

        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        cargarTexturas();
        fondo= new Fondo (texturaFondo);
        btnPlay = new BotonMenu(texturaBtnPlay);
        btnPlay2 = new BotonMenu2(texturaBtnPlay);
        btnPlay3 = new BotonMenu3(texturaBtnPlay);
        //btnPlay.setPosicion(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2);
        btnPlay.setPosicion(200,200);
        btnPlay2.setPosicion(400,400);
        btnPlay3.setPosicion(600,600);

        batch = new SpriteBatch();

    }

    private void cargarTexturas() {
        texturaFondo = new Texture(Gdx.files.internal("Menu.jpg"));
        texturaBtnPlay = new Texture(Gdx.files.internal("playBtn.png"));
        texturaBtnPlay2 = new Texture(Gdx.files.internal("playBtn.png"));
        texturaBtnPlay3 = new Texture(Gdx.files.internal("playBtn.png"));

    }

    @Override
    public void render(float delta) {

        //Borrar la Pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leerEntrada(); //Revisar touch
        batch.setProjectionMatrix(camara.combined);

        //Dibujar
        batch.begin();
        fondo.render(batch);
        btnPlay.render(batch);
        btnPlay2.render(batch);
        btnPlay3.render(batch);
        batch.end();

    }

    private void leerEntrada() {
        if (Gdx.input.justTouched()){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); //Traduce las coordenadas
            float x = coordenadas.x;
            float y = coordenadas.y;
            if (verificarBoton(x,y) == true){
                Gdx.app.log("leerEntrada","Tap sobre el botón");
                principal.setScreen(new PantallaJuego(principal));
            }
        }
    }

    private boolean verificarBoton(float x, float y) {
        Sprite sprite = btnPlay.getSprite();
        return x>=sprite.getX() && x<=sprite.getY() + sprite.getWidth() && y>= sprite.getY() && y<=sprite.getY()+sprite.getHeight();

        //return false;
    }

    @Override
    public void resize(int width, int height) {

        vista.update(width,height);

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

    }
    public void Back(){
        Gdx.input.setCatchBackKey(true);
    }
}