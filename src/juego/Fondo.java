package juego;

import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	Image imagenNombreJuego;
	Image imagenGanaste;
	Image imagenMenu;
	Image imagenFondoGano;
	Image imagenGO;
	
	Fondo(){
		imagenNombreJuego = Herramientas.cargarImagen("imagenes/superEli.png");
		imagenGanaste = Herramientas.cargarImagen("imagenes/ganaste2.png");
		imagenMenu = Herramientas.cargarImagen("imagenes/bodega.gif");
		imagenFondoGano = Herramientas.cargarImagen("imagenes/bosque.gif");
		imagenGO = Herramientas.cargarImagen("imagenes/gameOver1.gif");
	}
	
	public void dibujarMenu(Entorno entorno) { 
		entorno.dibujarImagen(imagenMenu, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
	}
	
	public void dibujarGO(Entorno entorno) { 
		entorno.dibujarImagen(imagenGO, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.0);
	}
	
	public void dibujarNombreJuego(Entorno entorno) { 
		entorno.dibujarImagen(imagenNombreJuego, entorno.ancho()/2, entorno.alto()/2, 0.0, 1.0);
	}
	
	public void dibujarNombreGanaste(Entorno entorno) { 
		entorno.dibujarImagen(imagenGanaste, entorno.ancho()/2, entorno.alto()/2, 0.0, 0.80);
	}
	
	public void dibujarFondoGano(Entorno entorno){
        entorno.dibujarImagen(imagenFondoGano, entorno.ancho()/2, entorno.alto()/2, 0.0, 2.25);
    }
	
	public static void mostrarVictoria(Entorno entorno, Fondo fondo, PrincesaPikachu princesaPikachu, Clip musicaJuego, Clip musicaGO, int puntos, Juego j) {
		musicaJuego.close();
		musicaGO.start();
		fondo.dibujarFondoGano(entorno);
		fondo.dibujarNombreGanaste(entorno);
		princesaPikachu.dibujarGano(entorno);
		princesaPikachu.dibujarGato(entorno);
		
		entorno.cambiarFont("Arial", 30, Color.white);
        entorno.escribirTexto("Press ENTER  to play",10,500 );
		
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			musicaGO.close();
			entorno.removeNotify();
			j = new Juego();
		}
	}
	
	public static void mostrarGameOver(Entorno entorno, Fondo fondo, PrincesaPikachu princesaPikachu, Clip musicaJuego, Clip musicaGO, Juego j) {
		musicaJuego.close();
		musicaGO.start();
		fondo.dibujarGO(entorno);
		princesaPikachu.dibujarPerdio(entorno); 
		entorno.cambiarFont("Arial", 30, Color.white);
        entorno.escribirTexto("Press ENTER  to play",10,500 );
		
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			musicaGO.close();
			entorno.removeNotify();
			j = new Juego();
		}
	}
	
	public static void mostrarPuntos(Entorno entorno, int puntos) {
		entorno.cambiarFont("Comic Sans MS", 40, Color.DARK_GRAY);
		entorno.escribirTexto("Points: " + puntos, 603, 103);
		entorno.cambiarFont("Comic Sans MS", 40, Color.RED);
		entorno.escribirTexto("Points: " + puntos, 600, 100);
	}
	
	public static void mostrarVidas(Entorno entorno, int vidas) {
		entorno.cambiarFont("Comic Sans MS", 40, Color.DARK_GRAY);
		entorno.escribirTexto("Lifes: " + vidas, 33, 103);
		entorno.cambiarFont("Comic Sans MS", 40, Color.GREEN);
		entorno.escribirTexto("Lifes: " + vidas, 30, 100);
	}
}
