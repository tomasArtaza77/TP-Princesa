package juego;

import java.awt.*;
import entorno.Herramientas;
import entorno.Entorno;
import java.util.Random;

public class Soldado {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private boolean avanza;
	private boolean ignoraToque;
	private Image img;
	@SuppressWarnings("unused")
	private double angulo;

	Soldado(double x, double y) {
		this.x = x;
		this.y = y;
		this.ancho = 70;
		this.alto  = 90;
		this.angulo = Math.PI / 4;
		this.img = Herramientas.cargarImagen("imagenes/enemigo 1.gif");
		this.avanza = true;
		ignoraToque = false;
	}

	public static Soldado[] inicializar() {
		Soldado[] s = new Soldado[3];
		s[0] = new Soldado(800 + xRandom(), 500);
		s[1] = new Soldado(1200 + xRandom(), 500);
		s[2] = new Soldado(1600 + xRandom(), 500);
		return s;
	}

	public static void dibujar(Entorno entorno, Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				entorno.dibujarImagen(s[i].img, s[i].x, s[i].y, 0, 0.3);
			}
		}
	}

	public static void mover(Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				s[i].mover();
			}
		}
	}
	
	public static void soldadoChocaObstaculo(Soldado[] soldados, Obstaculo[] obs) {
		for (int i = 0; i < soldados.length; i++) {
			for (int j = 0; j < obs.length; j++) {
				if ((soldados[i] != null) && (soldados[i].tocaObs(obs[j])) && (!soldados[i].ignoraToque)) {
					soldados[i].ignoraToque = true;
								
					if(soldados[i].avanza) {
						soldados[i].cambiarDeDireccion();
					}
					else {
						soldados[i].avanza = true;
					}
					
				} else if (soldados[i] != null){
					soldados[i].ignoraToque = false;
				}
			}
		}
	}
	
	/*
	private void cambiaDireccion() {
		if (avanza) {
			avanza = false;
		} else {
			avanza = true;
		}
	}
	*/
	
	void cambiarDeDireccion() {
		avanza = false;
		this.x+=10;
	}
	
	private boolean tocaObs(Obstaculo obs) {
		if (	this.x + this.ancho/2  >  obs.getX() - obs.getAncho()/2 &&
				this.x - this.ancho/2  <  obs.getX() + obs.getAncho()/2 &&
				this.y + this.ancho/2  >  obs.getY() - obs.getAlto()/2  &&
				this.y - this.ancho/2  <  obs.getY() + obs.getAlto()/2) {
			return true;
		}
		return false;
	}

	public static void reaparece(Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] == null) {
				s[i] = new Soldado( maximoX(s) + xRandom(), 500);
			}
			if (s[i] != null && (s[i].x <= -22.5) || (s[i].x > 2000)) {
				s[i] = null;
			}
		}
	}

	private static double xRandom() {
		Random r = new Random();
		return 800 + r.nextInt(400);
	}
	
	private static double maximoX(Soldado[] slds) {
		double max = 0;
		for (int i = 0; i < slds.length; i++) {
				if (slds[i] != null && slds[i].getX() > max) {
					max = slds[i].getX();
				}
			
		}
		return max;
	}

	private void mover() {
		if (avanza) {
			this.x += -2.0;
		} else {
			this.x += -0.5;
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
