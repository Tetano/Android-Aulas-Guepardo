package br.unitins.cge;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Principal extends Activity {

    //Declara referencia para a superficie de desenho
    private GLSurfaceView superficieDesenho = null;
    //declara referencia para o Render
    private Render render = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Instancia um objeto da superficie de desenho
        superficieDesenho = new GLSurfaceView(this);
        //instancia o objeto renderizador
        render = new Render(this);
        //Configura o objeto de desenho na superficie
        superficieDesenho.setRenderer(render);


        //Publicar a superficie de desenho na tela
        setContentView(superficieDesenho);
    }
}

class Render implements GLSurfaceView.Renderer {


    static final int TAMANHO = 200;


    private float largura = 0;
    private float altura;
    private Activity tela;
    private Imagem guepardo = null;
    private Imagem terra = null;
    private Imagem lua = null;
    private int angulo = 0;

    public Render(Activity tela) {
        this.tela = tela;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {


        //chamado quando a sup. é criada

        //define a cor de limpeza no formato RGBA
        gl.glClearColor(0, 0, 0, 0);
        gl.glClear(0);

    }

    private void configuraTela(GL10 gl, int width, int height) {
        //configurando a area de coordenadas do plano cartesiano
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        altura = height;
        largura = width;
        //configurando o volume de renderização
        gl.glOrthof(0, largura,
                0, altura,
                1, -1);

        //configurando a matriz de Transferencias geometricas
        //translação, rotação e escala
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        //configura a area de visualização na tela do DISP
        gl.glViewport(0, 0, width, height);

        //Habilita o desenho por vertices
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        //HABILITANTO A MÁQUINA OPENGL PARA O USO DE TEXTURAS
        gl.glEnable(GL10.GL_TEXTURE_2D);

        //HABILITA A MAQUINA PARA UTILIZAR UM VETOR DE COORDENADAS DE TEX
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);



    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        configuraTela(gl, width, height);
        guepardo = new Imagem(gl, TAMANHO*2,tela);
        guepardo.setXY(largura / 2, altura / 2);
        guepardo.setCor(Geometria.BRANCO);
        guepardo.setImagem(R.mipmap.guepardo);


        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        /*terra = new Imagem(gl,TAMANHO,tela);
        terra.setXY(100 + terra.tamanho,0)
                .setCor(Geometria.BRANCO);
        terra.setImagem(R.drawable.terra);

        lua = new Imagem(gl,TAMANHO/2,tela);
        lua.setXY(100+lua.tamanho,0).setCor(Geometria.BRANCO);
        lua.setImagem(R.drawable.lua);

        sol.empilhaImagem(terra);
        terra.empilhaImagem(lua);
*/
        //ASSINAR A TEXTURA QUE A OPENGL VAI UTILIZAR NO DESENHO DA PRIMITIVA
    }



    @Override
    public void onDrawFrame(GL10 gl) {
        //Desenha a tela (e mede o fps)

        //Aplica a cor de limpeza da tela a todos os bits do buffer de cor
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

       // sol.desenha();
      //  sol.setRotacao(angulo);
       // terra.setRotacao(angulo);
        //lua.setRotacao(angulo);
       guepardo.desenha();
        angulo++;
    }


}