package br.unitins.cge;

import android.app.Activity;

import javax.microedition.khronos.opengles.GL10;

public class Animacao extends Imagem{
    private float[] coordenadasTexturas;

    public Animacao(GL10 gl, int tamanho, Activity tela) {
        super(gl, tamanho, tela);
        for(int i=0;i<6;i++){
            coordenadasTexturas = new float[]{
                0,0
            };
        }


    }
}
