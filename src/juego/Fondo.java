package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	Image imagenNombreJuego;
	Image imagenGanaste;
	Image imagenMenu;
	Image imagenJuego;
	Image imagenGO; 
	
	Image imagenVictoria; 
	
	Fondo(){
		imagenNombreJuego = Herramientas.cargarImagen("imagenes/superEli.png");
		imagenGanaste = Herramientas.cargarImagen("imagenes/ganaste2.png");
		
		imagenMenu = Herramientas.cargarImagen("imagenes/bodega.gif");
		imagenJuego = Herramientas.cargarImagen("imagenes/bosque.gif");
		imagenGO = Herramientas.cargarImagen("imagenes/gameOver1.gif");
		
	}
	
	void dibujarMenu(Entorno entorno) { 
		entorno.dibujarImagen(imagenMenu, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
	}
	
	void dibujarGO(Entorno entorno) { 
		entorno.dibujarImagen(imagenGO, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.0);
	}
	
	void dibujarNombreJuego(Entorno entorno) { 
		entorno.dibujarImagen(imagenNombreJuego, entorno.ancho()/2, entorno.alto()/2, 0.0, 1.0);
	}
	
	void dibujarNombreGanaste(Entorno entorno) { 
		entorno.dibujarImagen(imagenGanaste, entorno.ancho()/2, entorno.alto()/2, 0.0, 0.80);
	}
	
	void dibujar(Entorno entorno){
        entorno.dibujarImagen(imagenJuego, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
    }
}
