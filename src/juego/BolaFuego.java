package juego;

import java.awt.Color;
import java.awt.*;
import entorno.Entorno;
import entorno.Herramientas;

public class BolaFuego {
	private boolean disparada;
	private double x;
	private double y;
	private double diametro;
	private double angulo;
	private Image imagen;
	
	BolaFuego() {
		disparada = false;
		this.x 		  = 200;	// provisorio
		this.y 		  = 510;	// provisorio
		this.diametro = 30;
		this.angulo   = 0;
		this.imagen = Herramientas.cargarImagen(""/*" nombre de la imagen.img "*/);
	}
	
	public void dibujarContorno(Entorno entorno) {
		entorno.dibujarCirculo(this.x, this.y, this.diametro, Color.yellow);
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo);
	}

	public boolean tocaSoldado(Soldado[] soldados) {
		if (this.disparada) {
			for (int i = 0; i < soldados.length; i++) {
				if (tocaSold(soldados[i])) {
					soldados[i].setQuemado(true);
					this.disparada = false;
					return true;
				}
			}
		}
		return false;
	}

	private boolean tocaSold(Soldado soldado) {
		if ( 	this.x + this.diametro/2 > soldado.getX() - soldado.getDiametro() &&
				this.x - this.diametro/2 < soldado.getX() + soldado.getDiametro() &&
				this.y + this.diametro/2 > soldado.getY() - soldado.getDiametro()) {
			return true;
		}
		return false;
	}
	
	public void tocaObstaculo(Obstaculo[] obstaculos) {
		for (int i = 0; i < obstaculos.length; i++) {
			if (tocaObs(obstaculos[i])) {
				this.disparada = false;
			}
		}
	}
	
	private boolean tocaObs(Obstaculo obstaculo) {
		if (	this.x + this.diametro/2	>	obstaculo.getX() - obstaculo.getAncho()/2 &&
				this.x - this.diametro/2	<	obstaculo.getX() + obstaculo.getAncho()/2 &&
				this.y + this.diametro/2	>	obstaculo.getY() - obstaculo.getAlto()/2  &&
				this.y - this.diametro/2	<	obstaculo.getY() + obstaculo.getAlto()/2) {
			this.disparada = false;
		}
		return false;
	}

	public void mover(Entorno entorno, Princesa princesa) {
		if (entorno.sePresiono(entorno.TECLA_ESPACIO) || this.disparada) {
			this.disparada = true;
			this.x += 6;
		} else {
			this.x = princesa.getX();
			this.y = princesa.getY();
		}
		if (this.x > 815) {
			this.disparada = false;
		}
	}
	
	public boolean disparada() {
		return this.disparada;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
