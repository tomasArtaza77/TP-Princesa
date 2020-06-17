package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double ang;
	private Image img;
	
	Obstaculo(int x, int y, int ancho, int alto /* imagen */) {
		this.x 		= x;
		this.y 		= y;
		this.ancho 	= ancho;
		this.alto 	= alto;
		this.ang   = 0;
		this.img = Herramientas.cargarImagen("imagenes/obstaculo.png");
	}

	public static Obstaculo[] inicializar() {
		Obstaculo[] obs = new Obstaculo[3];
		obs[0] = new Obstaculo(500, 500, 40, 90);
		obs[1] = new Obstaculo(800, 400, 40, 90);
		obs[2] = new Obstaculo(1000, 500, 40, 90);
		return obs;
	}
	
	public static void dibujar(Entorno entorno, Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			entorno.dibujarImagen(obs[i].img, obs[i].x, obs[i].y, obs[i].ang, 0.4);
		}
	}
	
	public static void mover(Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			obs[i].mover();
		}
	}

	private void mover() {
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
