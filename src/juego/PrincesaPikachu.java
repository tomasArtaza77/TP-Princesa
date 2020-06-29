package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class PrincesaPikachu {
	private double x;
	private double y;
	private double ancho;
	@SuppressWarnings("unused")
	private double alto;
	private double inercia;
	private Image img;
	private Image img2;
	private Image img3;
	private Image img4;
	private boolean salto;
	private boolean vulnerable;
	
	PrincesaPikachu() {
		this.x = 200;
		this.y = 525;
		this.ancho = 50;
		this.alto  = 30;
		this.img = Herramientas.cargarImagen("imagenes/Pikaa.gif");
		this.img2 = Herramientas.cargarImagen("imagenes/pikaGano.gif");
		this.img3 = Herramientas.cargarImagen("imagenes/gato1.gif");
		this.img4 = Herramientas.cargarImagen("imagenes/pikaPerdio.gif");
		this.salto = false;
		this.inercia = 9.0;
		this.vulnerable = true;
	}
	
	public void dibujarPersonaje(Entorno entorno) {
		entorno.dibujarImagen(this.img, this.x-12, this.y, 0, 0.2);
	}
	
	public void dibujarGano(Entorno entorno) {
		entorno.dibujarImagen(this.img2, entorno.ancho()/2, 510, 0, 0.3);
	}
	
	public void dibujarPerdio(Entorno entorno) {
		entorno.dibujarImagen(this.img4, entorno.ancho()/2+100, 475, 0, 0.125);
	}
	
	public void dibujarGato(Entorno entorno) {
		entorno.dibujarImagen(this.img3, entorno.ancho()/2+80, 510, 0, 0.125);
	}
	
	public boolean siChoca(Obstaculo[] obstaculos, Soldado[] soldados) {
		if (tocaObstaculo(obstaculos) || tocaSoldado(soldados)) {
			return true;
		}
		return false;
	}
	
	private boolean tocaObstaculo(Obstaculo[] obs) {
		for (int i = 0; i < obs.length; i++) {
			if (	this.x + this.ancho/2  >  obs[i].getX() - obs[i].getAncho()/2 &&
					this.x - this.ancho/2  <  obs[i].getX() + obs[i].getAncho()/2 &&
					this.y + this.ancho/2  >  obs[i].getY() - obs[i].getAlto()/2  &&
					this.y - this.ancho/2  <  obs[i].getY() + obs[i].getAlto()/2) {
				return true;
			}
		}
		return false;
	}
	
	private boolean tocaSoldado(Soldado[] sold) {
		for (int i = 0; i < sold.length; i++) {
			if (	sold[i] != null &&
					this.x + this.ancho/2  >  sold[i].getX() - sold[i].getAncho()/2 &&
					this.x - this.ancho/2  <  sold[i].getX() + sold[i].getAncho()/2 &&
					this.y + this.ancho/2  >  sold[i].getY() - sold[i].getAlto()/2  &&
					this.y - this.ancho/2  <  sold[i].getY() + sold[i].getAlto()/2) {
				return true;
			}
		}
		return false;
	}
	
	public void impulso() {
		if (!this.salto) {
			this.inercia = -9.0;
		}
		if (this.salto) {
			this.y += this.inercia;
			this.inercia += 0.15;
		}
		if (this.y >= 510) {
			this.salto = false;
			this.y = 510;
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
				fb[i] = new Fireball(this.x + this.ancho/2, this.y);
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
