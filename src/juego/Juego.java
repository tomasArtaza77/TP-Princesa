package juego;

import java.awt.Color;
import entorno.Entorno;
//import entorno.Herramientas;
import entorno.InterfaceJuego;
//import javax.sound.sampled.Clip;
//import java.awt.Color;
// el nombre del juego es Super Elizabeth Sis
// CAMBIAR NOMBRES A GETTERS PORQUE QUEDAN FEOS
// ACORTAR NOMBRES LARGOS DE VARIABLES (OBSTACULOS = OBS)
// a private los metodos auxiliares
public class Juego extends InterfaceJuego {
	private Entorno 	entorno;
	private Princesa 	princesa;
	private BolaFuego 	bolaFuego;			// podria ser una lista o arreglo para tener varios disparos
	private Obstaculo[] obstaculos;
	private Soldado[] 	soldados;
	private Fondo 		fondo;
	private int 		puntos;
	private int 		vidas;
//	Clip musicaJuego;
//	-----------------------------------------------------------------------------------
	public Juego() {
		this.entorno = new Entorno(this, "Super Elizabeth Sis", 800, 600);
		fondo 		= new Fondo();
		princesa 	= new Princesa();
		bolaFuego	= new BolaFuego();
//		musicaJuego = Herramientas.cargarSonido("musica/CC.wav");
		obstaculos	= Obstaculo.inicializar();
		soldados	= Soldado.inicializar();
		puntos		= 0;
		vidas		= 3;
		this.entorno.iniciar();
	}
//	-----------------------------------------------------------------------------------
	@SuppressWarnings("unused")
	public void tick() {
		if (vidas > 0) {
			fondo		.dibujar(entorno);
//			musicaJuego	.start();
			princesa		.dibujarContorno(entorno);				// borrar cuando no se use
			princesa		.dibujar(entorno);
			bolaFuego	.dibujarContorno(entorno);
//			Soldado		.dibujar(entorno, soldados);	faltan las imagenes
//			Obstaculo	.dibujar(entorno, obstaculos);	faltan las imagenes
			Obstaculo	.dibujarContorno(entorno, obstaculos);	// borrar
			Soldado		.dibujarContorno(entorno, soldados);	// borrar
			Obstaculo	.mover(obstaculos);
			Soldado		.mover(soldados);
			mostrarPuntos();
			mostrarVidas();
		
			princesa.saltar(entorno);
		
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				princesa.avanzar();	
			}
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				princesa.retroceder();
			}
			if (princesa.siChoca(obstaculos, soldados) && princesa.isVulnerable()) {
				princesa.setVulnerable(false);
				vidas--;
			}
			if (vidas == 0) {
//				musicaJuego.close();
			}
			if (!princesa.siChoca(obstaculos, soldados)){
				princesa.setVulnerable(true);
			}
		
			bolaFuego.mover(entorno, princesa);
			bolaFuego.tocaObstaculo(obstaculos);
		
			if (bolaFuego.tocaSoldado(soldados)) {
				puntos += 5;
			}
		}
		if (vidas == 0) {
			mostrarGameOver();
		}
		//fin();
	}
//	-----------------------------------------------------------------------------------
	public void mostrarPuntos() {
		entorno.cambiarFont("Comic Sans MS", 40, Color.darkGray);
		entorno.escribirTexto("Puntos: " + puntos, 603, 103);	// INFO EN PANTALLA
		entorno.cambiarFont("Comic Sans MS", 40, Color.RED);	// demasiadas lineas
		entorno.escribirTexto("Puntos: " + puntos, 600, 100);	// se podrian meter en un metodo
	}
	public void mostrarVidas() {
		entorno.cambiarFont("Comic Sans MS", 40, Color.darkGray);
		entorno.escribirTexto("Vidas: " + vidas, 33, 103);
		entorno.cambiarFont("Comic Sans MS", 40, Color.GREEN);
		entorno.escribirTexto("Vidas: " + vidas, 30, 100);
	}
	
	public void mostrarGameOver() {
		entorno.cambiarFont("Comic Sans MS", 60, Color.darkGray);
		entorno.escribirTexto("Game Over :'(", 203, 303);
		entorno.cambiarFont("Comic Sans MS", 60, Color.RED);
		entorno.escribirTexto("Game Over :'(", 200, 300);
	}
	
	@SuppressWarnings("unused") //Si no pongo esto se rompe todo
	public void fin() {
		//Termina musica del juego
		if (vidas==0) {
//			musicaJuego.close(); 
			GameOver go = new GameOver();
		}
	}
//	-----------------------------------------------------------------------------------


}
