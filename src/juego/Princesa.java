package juego;

import java.awt.Image;

//import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Princesa {
	private double x;
	private double y;
	private double diam;
	private double ang;
	private Image img;
	private Image img2;
	private Image img3;
	private Image img4;
	private boolean salto;
	private double inercia;
	private boolean vulnerable;
	
	//private Clip saltar;
	
	
	Princesa() {
		this.x = 200;
		this.y = 510;
		this.diam = 50;
		this.ang = 2 * Math.PI;
		this.img = Herramientas.cargarImagen("imagenes/pika.gif");
		this.img2 = Herramientas.cargarImagen("imagenes/pikaGano.gif");
		this.img3 = Herramientas.cargarImagen("imagenes/gato1.gif");
		this.img4 = Herramientas.cargarImagen("imagenes/pikaPerdio.gif");
		//saltar       = Herramientas.cargarSonido("musica/salto.wav");
		
		this.salto = false;
		this.inercia = 0.0;
		this.vulnerable = true;
	}
	
	void dibujarPersonaje(Entorno entorno) {
		entorno.dibujarImagen(this.img, this.x, this.y, this.ang, 0.2);
	}
	
	void dibujarGano(Entorno entorno) { //dibuja el pikachu cuando ganas
		entorno.dibujarImagen(this.img2, entorno.ancho()/2, 510, this.ang, 0.3);
	}
	
	void dibujarPerdio(Entorno entorno) { //dibuja el pikachu cuando ganas
		entorno.dibujarImagen(this.img4, entorno.ancho()/2+100, 475, this.ang, 0.125);
	}
	
	void dibujarGato(Entorno entorno) {
		entorno.dibujarImagen(this.img3, entorno.ancho()/2+80, 510, this.ang, 0.125);
	}
	
	boolean siChoca(Obstaculo[] obstaculos, Soldado[] soldados) {
		if (tocaObstaculo(obstaculos) || tocaSoldado(soldados)) {
			return true;
		}
		return false;
	}
	
	private boolean tocaObstaculo(Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			if (	this.x + this.diam/4  >  obs[i].getX() - obs[i].getAncho()/2 &&	// /4 para que no sea tan "celoso" el contacto
					this.x - this.diam/4  <  obs[i].getX() + obs[i].getAncho()/2 &&
					this.y + this.diam/4  >  obs[i].getY() - obs[i].getAlto()/2  &&
					this.y - this.diam/4  <  obs[i].getY() + obs[i].getAlto()/2) {
				return true;
			}
		}
		return false;
	}
	
	private boolean tocaSoldado(Soldado[] sold) {
		for (int i = 0; i < sold.length; i++) {
			if (this.distancia(sold[i]) < this.diam/2 + sold[i].getDiametro() ) {
				return true;
			}
		}
		return false;
	}
	
	private double distancia(Soldado s) {	// Pitagoras rudimentario
		double cat_a = Math.abs(this.x - s.getX());
		double cat_b = Math.abs(this.y - s.getY());
		double sum_cat2 = Math.pow(cat_a, 2) + Math.pow(cat_b, 2);
		double hip = Math.sqrt(sum_cat2);
		return hip;
	}
	
	public void impulso() {
		if (!this.salto) {
			this.inercia = -9;
		}
		if (this.salto) {
			this.y += this.inercia;
			this.inercia += 0.18;
		}
		if (this.y >= 510) {
			this.salto = false;
		}
	}
	
	public void avanzar() {
		if (this.x < 350 && !this.salto) {
			this.x += 2;
		}
	}

	public void retroceder() {
		if (this.x > 40 && !this.salto) {
			this.x -= 2;
		}
	}
	
	public void disparar(Fireball[] fb) {
		for (int i = 0; i < fb.length; i++) {
			if (fb[i] == null) {
				fb[i] = new Fireball(this.x, this.y);
				return;
			}
		}
	}
	
	public void setSalto(boolean salto) {
		this.salto = salto;
		
	}

	public boolean esVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
}
