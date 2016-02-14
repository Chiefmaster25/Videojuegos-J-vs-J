package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by AllanIvan on 02/02/2016.
 */
public class PantallaJuego implements Screen {

    public static final int MAX_TIEMPO_SIN_GOLPE = 7;
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    private Array<Hoyo> hoyos;
    private Texture texturaHoyo;

    //Arreglo de Topos
    private Array <Hoyo> topos;
    private Texture texturaTopo;

    //Fondo
    private Fondo fondo;
    private Texture texturaFondo;

    private final Principal principal;

    //Crear Marcador
    private int marcador;

    //Texto
    private Texto texto;

    private Sound efectoGolpe;
    private Music musicaFondo;

    //Tiempo no golpear topos

    private float tiempoSinGolpe = MAX_TIEMPO_SIN_GOLPE;

    //Para Enum State
    private State state = State.RUN;

    public PantallaJuego(Principal principal) {
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
        fondo= new Fondo (texturaFondo);

        batch = new SpriteBatch();

        //Hoyos
        crearHoyos();

        texto = new Texto();


        cargarAudio();

    }

    private void cargarAudio() {
        efectoGolpe = Gdx.audio.newSound(Gdx.files.internal("pacman-dies.wav"));

        musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("enjoy.mp3"));
        musicaFondo.setLooping(true);
        musicaFondo.play();
    }

    private void crearHoyos() {
        hoyos = new Array<Hoyo>(9);
        for (int i = 0; i < 9; i++){ //hoyos.size//; i++) {
            Hoyo nuevo = new Hoyo(texturaHoyo);
            nuevo.setPosicion(200 + (i % 3) * 350, 200 + 150 * (i / 3));
            hoyos.add(nuevo);
            Gdx.app.log("Crea hoyo", "numero " + i);
        }
        //Crear Topos
        topos = new Array<Hoyo>(9);
        for (int i = 0; i < 9; i++){ //hoyos.size//; i++) {
            Hoyo nuevo = new Hoyo(texturaTopo);
            nuevo.setPosicion(250 + (i % 3) * 350, 230 + 150 * (i / 3));
            topos.add(nuevo);
        }
    }

    private void cargarTexturas() {
        texturaFondo = new Texture(Gdx.files.internal("fondoPasto.jpg"));
        texturaHoyo = new Texture(Gdx.files.internal("hole.png"));
        texturaTopo = new Texture(Gdx.files.internal("mole.png"));
    }


    private void leerEntrada() {
        if (Gdx.input.justTouched()){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas); //Traduce las coordenadas
            float x = coordenadas.x;
            float y = coordenadas.y;
            //Revisar si tocó algún topo
            for(Hoyo topo:
                topos){
                if (revisarTouch(topo,x,y)){
                    if (topo.getEstado()!= Hoyo.Estado.MURIENDO) {
                        topo.ocultar();
                        marcador++;
                        tiempoSinGolpe = MAX_TIEMPO_SIN_GOLPE;
                        //Reproducir el efecto
                        efectoGolpe.play();
                    }
                    break;
                }
            }
        }
    }

    private boolean revisarTouch(Hoyo topo, float x, float y) {
        if (x>=topo.getSprite().getX()
                && x<=topo.getSprite().getX()+topo.getSprite().getWidth()
                && y>=topo.getSprite().getY()
                && y<=topo.getSprite().getY()+topo.getSprite().getHeight()){
            return true;
        }
        return false;
    }

    @Override
    public void render(float delta) {
        //Lectura de entrada
        leerEntrada();

        probarTiempoGolpe();

        //Borrar la Pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //--leerEntrada(); //Revisar touch
        batch.setProjectionMatrix(camara.combined);

        //Dibujar
        batch.begin();
        fondo.render(batch);
        for (Hoyo hoyo:
                hoyos){
            hoyo.render(batch);
        }
        //Topos
        for (Hoyo topo :
                topos){
            topo.render(batch);
            //topo.setPosicion(topo.getSprite().getX()+1, topo.getSprite().getY());
            topo.actualizar();
        }

        //Mostrar marcador
        //btnPlay.render(batch);
        texto.mostrarMensaje(batch, "Puntos: " + marcador,
                Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO * .09f);

        texto.mostrarMensaje(batch, "Tiempo: " + tiempoSinGolpe,
                Principal.ANCHO_MUNDO*0.1f, Principal.ALTO_MUNDO*0.9f);
        batch.end();

        switch (state) {
            case RUN:
//do suff here
                break;
            case PAUSE:
//do stuff here
                break;
            case RESUME:

                break;

            default:
                break;
        }
    }

    private void probarTiempoGolpe(){
        if (tiempoSinGolpe<=0){
            principal.setScreen(new PantallaMenu(principal));
        }
        else{
            tiempoSinGolpe -= Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    //@Override
    //public void pause() {

//    }

    //@Override
  //  public void resume() {

    //}

    @Override
    public void hide() {
        if (musicaFondo.isPlaying()){
            musicaFondo.stop();
        }
        musicaFondo.stop();
    }

    @Override
    public void dispose() {
        texturaTopo.dispose();
        texturaHoyo.dispose();
        texturaFondo.dispose();
        efectoGolpe.dispose();
        musicaFondo.dispose();
    }

    public enum State{
        PAUSE,
        RUN,
        RESUME,
        STOPPED
    }

    @Override
    public void pause()
    {
        this.state = State.PAUSE;
    }

    @Override
    public void resume()
    {
        this.state = State.RESUME;
    }
    public void setGameState(State s){
        this.state = s;
    }
}
