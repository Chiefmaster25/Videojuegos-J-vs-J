package mx.itesm.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by AllanIvan on 02/02/2016.
 */
public class Hoyo {
    private Sprite sprite;

    //Para los topos violadores estilo pitochu :v
    private float alturaActual;
    private float ancho, alto;
    private float velocidad;
    private Estado estado; //Subiendo, bajando, etc.
    private float tiempoOculto; //Lo que lleva oculto
    private float maxTiempoOculto; //(

    //Efecto morir
    private float tiempoMuriendo;

    public Hoyo (Texture textura){

        sprite= new Sprite(textura);
        //velocidad = 0.1f;
        velocidad = 5f;
        ancho = sprite.getWidth();
        alto = sprite.getHeight();
        alturaActual = alto;
        estado = Estado.BAJANDO;
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

    public void actualizar() {
        switch (estado) {
            case BAJANDO:
                alturaActual -= velocidad;
                if (alturaActual <= 0) {
                    alturaActual = 0;
                    estado = Estado.OCULTO;
                    tiempoOculto = 0;
                    maxTiempoOculto = tiempoAzar();
                }
                break;
            case SUBIENDO:
                alturaActual += velocidad;
                if (alturaActual >= alto) {
                    alturaActual = alto; //original
                    estado = Estado.BAJANDO;
                }
                break;
            case OCULTO:
                tiempoOculto += Gdx.graphics.getDeltaTime();
                if (tiempoOculto>=maxTiempoOculto){
                    estado = Estado.SUBIENDO;
                }
                break;
            case MURIENDO:
                sprite.setAlpha(0.5f);
                tiempoMuriendo -= Gdx.graphics.getDeltaTime();
                sprite.setRotation(sprite.getRotation()+30);
                if (tiempoMuriendo<=0){
                    estado = Estado.OCULTO;
                    tiempoOculto = 0;
                    alturaActual = 0;
                    maxTiempoOculto = (float) Math.random()*2;
                    sprite.setRotation(0);
                    sprite.setAlpha(1);
                    sprite.setColor(Color.WHITE);
                }
                break;
        }
        sprite.setRegion(0,0,(int)ancho, (int)alturaActual);
        sprite.setSize(ancho,alturaActual);
    }

    private float tiempoAzar() {
        return (float)(Math.random()*2+0.3f);
    }

    public void ocultar() {
        if (estado!=Estado.MURIENDO) {
            estado = Estado.MURIENDO;
            tiempoMuriendo = tiempoAzar();
            //alturaActual = 0;
            //tiempoOculto = 0;
            //maxTiempoOculto = tiempoAzar();
            sprite.setColor(Color.RED);
        }
    }

    public Estado getEstado() {
        return estado;
    }

    //Estados
    public enum Estado {
        SUBIENDO,
        BAJANDO,
        OCULTO,
        MURIENDO
    }

}