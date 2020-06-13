package juego;

import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;
// PONER this. A TODAS LAS VARIABLES DE INSTANCIA
public class Princesa {
	private double x;
	private double y;
	private double diametro;
	private double angulo;
	private Image imagen;
	private boolean salto;
	private double inercia;
	private boolean vulnerable;
	
	Princesa() {
		this.x = 200;
		this.y = 510;
		this.diametro = 50;
		this.angulo = 2 * Math.PI;
		this.imagen = Herramientas.cargarImagen("imagenes/pika.gif");
		this.salto = false;		// cambia a true cuando se presiona arriba y a false cuando "toca" el suelo
		this.inercia = 0.0;		// la velocidad de ascenso y descenso
		this.vulnerable = true;
	}
	
	void dibujarContorno(Entorno entorno) {		// BORRAR CUANDO NO SE NECESITE
		entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.yellow);
	}
	
	void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo, 0.2);
	}
	
	boolean siChoca(Obstaculo[] obstaculos, Soldado[] soldados) {	// faltan las colisiones con los soldados
		if (tocaObstaculo(obstaculos) || tocaSoldado(soldados)) {
			return true;
		}
		return false;
	}
	
	private boolean tocaObstaculo(Obstaculo[] obstaculos) {
		for (int i = 0; i < obstaculos.length; i++) {
			if (	this.x > obstaculos[i].getX() - obstaculos[i].getAncho()/2 &&
					this.x < obstaculos[i].getX() + obstaculos[i].getAncho()/2 &&
					this.y > obstaculos[i].getY() - obstaculos[i].getAlto()/2  &&
					this.y < obstaculos[i].getY() + obstaculos[i].getAlto()/2) {
				return true;
			}
		}
		return false;
	}
	
	private boolean tocaSoldado(Soldado[] soldados) {
		for (int i = 0; i < soldados.length; i++) {
			if (	this.x > soldados[i].getX() - soldados[i].getDiametro()/2 &&
					this.x < soldados[i].getX() + soldados[i].getDiametro()/2 &&
					this.y > soldados[i].getY() - soldados[i].getDiametro()/2  &&
					this.y < soldados[i].getY() + soldados[i].getDiametro()/2) {
				return true;
			}
		}
		return false;
	}
	
	void saltar(Entorno entorno) {
		if (entorno.sePresiono(entorno.TECLA_ARRIBA) && !this.salto) {
			this.inercia = -8;	// setea el empuje hacia arriba
			this.salto = true;	// esta saltando
		}
		if (this.salto) {
			this.y += this.inercia;	// le suma al valor en el eje y la inercia
			this.inercia += 0.17;	// va achicando la inercia para que el salto se vaya frenando
		}
		if (this.y >= 510) {
			this.salto = false; // cuando llega al nivel del suelo, salto = false
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
	
	public boolean saltando() {
		return salto;
	}

	public boolean isVulnerable() {	// nombres feos!!
		return vulnerable;
	}

	public void setVulnerable(boolean vulnerable) {	// cambiar "getter" y "setter" por otra cosa
		this.vulnerable = vulnerable;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
	
	
	
	
	
	
}
