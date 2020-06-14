package juego;

import java.awt.*;
import entorno.Herramientas;
import entorno.Entorno;
//import java.util.Random;

public class Soldado {
	private double x;
	private double y;
	private double diametro;
	private boolean quemado;
	private Image imagen;
	private double angulo;

	
	
	Soldado(double x, double y, double diametro /* imagen */) {
		this.x 		  = x;
		this.y 		  = y;
		this.diametro = diametro;
		this.quemado  = false;
		this.angulo   = 0;
		this.imagen = Herramientas.cargarImagen("imagenes/enemigo 1.gif");
	}

	public static Soldado[] inicializar() {
		Soldado[] soldados = new Soldado[3];
		soldados[0] = new Soldado(475, 515, 45);
		soldados[1] = new Soldado(600, 515, 45);
		soldados[2] = new Soldado(900, 515, 45);
		return soldados;
	}
	/*
	public static void dibujarContorno(Entorno entorno, Soldado[] soldados) {		// BORRAR CUANDO NO SE NECESITE
		for (int i = 0; i < soldados.length; i++) {
			entorno.dibujarCirculo(soldados[i].x, soldados[i].y, soldados[i].diametro, Color.red);
		}
	}
	*/
	public static void dibujarSoldado(Entorno entorno, Soldado[] soldados) {
		for (int i = 0; i < soldados.length; i++) {
			entorno.dibujarImagen(soldados[i].imagen, soldados[i].x, soldados[i].y, soldados[i].angulo, 0.3);
		}
	}
	
	public static void mover(Soldado[] soldados) {
		for (int i = 0; i < soldados.length; i++) {
			soldados[i].mover();
		}
	}

	private void mover() {	// FALTA POSICION RANDOM DE SOLDADOS Y NO-SUPERPOSICION
		this.x -= 2.0;
		if (this.x <= -12.5 || this.quemado) {
//			Random random = new Random();		// para que varie la distancia entre obstaculos
//			this.x = 1000 + random.nextInt(500);
			this.x = 812.5;
			this.quemado = false;
		}
	}

	public boolean isQuemado() {
		return quemado;
	}

	public void setQuemado(boolean quemado) {
		this.quemado = quemado;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDiametro() {
		return diametro;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
