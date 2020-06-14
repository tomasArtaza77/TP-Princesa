package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private Image imagen;
	
	Obstaculo(int x, int y, int ancho, int alto /* imagen */) {
		this.x 		= x;
		this.y 		= y;
		this.ancho 	= ancho;
		this.alto 	= alto;
		this.angulo   = 0;
		this.imagen = Herramientas.cargarImagen("imagenes/obstaculo.png");
	}

	public static Obstaculo[] inicializar() {
		Obstaculo[] obstaculos = new Obstaculo[3];
		obstaculos[0] = new Obstaculo(500, 500, 40, 90);
		obstaculos[1] = new Obstaculo(700, 400, 40, 90);
		obstaculos[2] = new Obstaculo(850, 500, 40, 90);
		return obstaculos;
	}
	/*
	public static void dibujarContorno(Entorno entorno, Obstaculo[] obstaculos) {
		for (int i = 0; i < obstaculos.length; i++) {
			entorno.dibujarRectangulo(obstaculos[i].x, obstaculos[i].y, obstaculos[i].ancho, obstaculos[i].alto, 0, Color.cyan);
		}
	}
	*/
	public static void dibujarObstaculo(Entorno entorno, Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			entorno.dibujarImagen(obs[i].imagen, obs[i].x, obs[i].y, obs[i].angulo, 0.4);
		}
	}
	
	public static void mover(Obstaculo[] obstaculos) {
		for (int i = 0; i < obstaculos.length; i++) {
			obstaculos[i].mover();
		}
	}

	public void mover() {
		this.x -= 1.5;
		if (this.x <= -20) {
			this.x = 840;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
	



}
