package juego;

import java.awt.Image;

import javax.sound.sampled.Clip;

//import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

//import entorno.Herramientas;
public class Fireball {
	private double x;
	private double y;
	private double diam;
	private static Clip disparo;
	private double ang;
	private Image img;
	private Clip explosion;

	Fireball(double x, double y) {
		this.x = x;
		this.y = y;
		this.diam = 30;
		disparo = Herramientas.cargarSonido("musica/disparo.wav");
		explosion = Herramientas.cargarSonido("musica/explosion.wav");
		this.img = Herramientas.cargarImagen("imagenes/bola.gif");
		this.ang = 0;

	}

	public static void dibujar(Entorno entorno, Fireball[] fb) {
		for (int i = 0; i < fb.length; i++) {
			if (fb[i] != null) {
				entorno.dibujarImagen(fb[i].img, fb[i].x, fb[i].y, fb[i].ang, 0.2);
				disparo.start(); // efecto disparo

			}
		}
	}

	public boolean tocaSoldado(Soldado[] sold) {
		for (int i = 0; i < sold.length; i++) {
			if (sold[i] != null) {
				if (tocaSold(sold[i])) {
					explosion.start(); // efecto mueren soldados
					sold[i] = null;
					return true;
				}
			}
		}
		return false;
	}

	private boolean tocaSold(Soldado sold) {
		if (this.distancia(sold) < this.diam / 2 + sold.getDiametro() / 2) {
			return true;
		}
		return false;
	}

	private double distancia(Soldado sold) {// Pitagoras rudimentario
		double cat_a = Math.abs(this.x - sold.getX());
		double cat_b = Math.abs(this.y - sold.getY());
		double sum_cat2 = Math.pow(cat_a, 2) + Math.pow(cat_b, 2);
		double hip = Math.sqrt(sum_cat2);
		return hip;
	}

	public boolean tocaObstaculo(Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			if (tocaObs(obs[i])) {
				return true;
			}
		}
		return false;
	}

	private boolean tocaObs(Obstaculo o) {
		if (this.x + this.diam / 2 > o.getX() - o.getAncho() / 2 && this.x - this.diam / 2 < o.getX() + o.getAncho() / 2
				&& this.y + this.diam / 2 > o.getY() - o.getAlto() / 2
				&& this.y - this.diam / 2 < o.getY() + o.getAlto() / 2) {
			return true;
		}
		return false;
	}

	public static void mover(Fireball[] fb) {
		for (int i = 0; i < fb.length; i++) {
			if (fb[i] != null) {
				fb[i].x += 6;
			}
		}
	}

	public double getX() {
		return x;
	}

}
