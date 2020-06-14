package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	Image imagenNombre;
	Image imagenMenu;
	Image imagenJuego;
	Image imagenGO;
	private int x; 
	
	public Fondo(Image imagen1, Image imagen2, int x, int y) {
		this.imagenMenu = imagen1;
		this.imagenJuego = imagen2;
	}
	
	Fondo(){
		imagenNombre = Herramientas.cargarImagen("imagenes/princesaMenu.png");
		imagenMenu = Herramientas.cargarImagen("imagenes/bodega.gif");
		imagenJuego = Herramientas.cargarImagen("imagenes/bosque.gif");
		imagenGO = Herramientas.cargarImagen("imagenes/dragon.gif");
	}
	/*
	void dibujarFondo(Entorno entorno){
        entorno.dibujarImagen(imagen2, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.0);
    }
    */
	
	void dibujarMenu(Entorno entorno) { 
		entorno.dibujarImagen(imagenMenu, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
	}
	
	void dibujarGO(Entorno entorno) { 
		entorno.dibujarImagen(imagenGO, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
	}
	
	void dibujarNombreGO(Entorno entorno) { 
		entorno.dibujarImagen(imagenNombre, entorno.ancho()/2, entorno.alto()/2, 0.0, 1.0);
	}
	
	void dibujarNombreJuego(Entorno entorno) { 
		entorno.dibujarImagen(imagenNombre, entorno.ancho()/2, entorno.alto()/2, 0.0, 1.0);
	}
	
	
	
	void dibujar(Entorno entorno){
        entorno.dibujarImagen(imagenJuego, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
    }
	
	public void mover(int modificador) {
		this.x = this.x - modificador;
		//y += velocidad * Math.sin(angulo);
	}
	
}

