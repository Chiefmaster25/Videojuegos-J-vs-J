package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
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
    private BotonExit btnExit;

    private Texture texturaBtnPlay;
    private Texture texturaBtnPlay2;
    private Texture texturaBtnPlay3;
    private Texture texturaBtnExit;

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
        btnPlay2 = new BotonMenu2(texturaBtnPlay2);
        btnPlay3 = new BotonMenu3(texturaBtnPlay3);
        btnExit = new BotonExit(texturaBtnExit);
        //btnPlay.setPosicion(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2);

        btnPlay.setPosicion(50,150);
        btnPlay2.setPosicion(150,300);
        btnPlay3.setPosicion(250,450);
        btnExit.setPosicion(900,50);
        //btnExit.setPosicion(0,0);
        btnExit.setSize(300,300);

        batch = new SpriteBatch();

    }


    private void cargarTexturas() {
        texturaFondo = new Texture(Gdx.files.internal("Menu.jpg"));
        texturaBtnPlay = new Texture(Gdx.files.internal("play.jpg"));
        texturaBtnPlay2 = new Texture(Gdx.files.internal("como.jpg"));
        texturaBtnPlay3 = new Texture(Gdx.files.internal("retos.jpg"));
        texturaBtnExit = new Texture(Gdx.files.internal("exit.jpg"));

    }

    @Override
    public void render(float delta) {

        //Borrar la Pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leerEntrada(); //Revisar touch
        leerSalida(); //Botón para salir

        batch.setProjectionMatrix(camara.combined);

        //Dibujar
        batch.begin();
        fondo.render(batch);
        btnPlay.render(batch);
        btnPlay2.render(batch);
        btnPlay3.render(batch);
        btnExit.render(batch);
        batch.end();

    }

//    InputProcessor backProcessor = new InputAdapter() {
  //      @Override
//        public boolean keyDown(int keycode) {
//
//            if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) )

                // Maybe perform other operations before exiting
//                Gdx.app.exit();
//            return false;
 //       }
 //   };


    private void leerEntrada() {
        if (Gdx.input.justTouched()) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); //Traduce las coordenadas
            float x = coordenadas.x;
            float y = coordenadas.y;
            if (verificarBoton(x,y) == true){
                Gdx.app.log("leerEntrada","Tap sobre el botón");
                principal.setScreen(new PantallaJuego(principal));
                //Gdx.app.exit();
            }
        }
    }

    private void leerSalida(){
        if (Gdx.input.justTouched()) {
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); //Traduce las coordenadas
            float x = coordenadas.x;
            float y = coordenadas.y;
            if (verificarSalida(x, y) == true){
                Gdx.app.log("leerSalida", "Tap sobre el botón");
                //Gdx.input.setCatchBackKey(true);
                Gdx.app.exit();
            }
        }
    }

    private boolean verificarBoton(float x, float y) {
        Sprite sprite = btnPlay.getSprite();
        return x>=sprite.getX() && x<=sprite.getY() + sprite.getWidth() && y>= sprite.getY() && y<=sprite.getY()+sprite.getHeight();
        //return false;
    }

    private boolean verificarSalida(float x, float y){
        Sprite sprite = btnExit.getSprite();
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
    //public void Back(){
      //  Gdx.input.setCatchBackKey(true);
    //}
}