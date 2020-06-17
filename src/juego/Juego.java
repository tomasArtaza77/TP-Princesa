package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;
// CAMBIAR NOMBRES A GETTERS PORQUE QUEDAN FEOS
// ACORTAR NOMBRES LARGOS DE VARIABLES (OBSTACULOS = OBS)
// a private los metodos auxiliares
public class Juego extends InterfaceJuego {
	private Entorno 	entorno;
	private Princesa 	princesa;
	Juego j;
	
	private Fireball[]	fireballs;
	private Obstaculo[] obstaculos;
	private Soldado[] 	soldados;
	private Fondo 		fondo;
//	private Suelo[]		suelo;
	private moverFondo[] movFondo;
	private int 		puntos;
	private int 		vidas;
	private boolean		terminado;
	private Clip 		musicaGO;
	private Clip 		musicaJuego;
	//private Clip 		saltar;
	
//	-----------------------------------------------------------------------------------
	public Juego() {
		this.entorno  = new Entorno(this, "Super Elizabeth Sis", 800, 600);
		fondo 		  = new Fondo();
		princesa 	  = new Princesa();
		fireballs	  = new Fireball[10];				// se crea cuando la princesa dispara
		
		musicaGO	  = Herramientas.cargarSonido("musica/Game Over.wav");
		musicaJuego   = Herramientas.cargarSonido("musica/musicaJuego.wav");
		//saltar       = Herramientas.cargarSonido("musica/salto.wav");
		
		//suelo		  = Suelo.inicializar();
		movFondo	  = moverFondo.inicializar();
		obstaculos	  = Obstaculo.inicializar();
		soldados	  = Soldado.inicializar();
		puntos		  = 0;
		vidas		  = 3;
		terminado	  = false;
		
		this.entorno.iniciar();
	}
//	-----------------------------------------------------------------------------------
	public void tick() {
		if (!terminado) {
			fondo		.dibujar(entorno);
			//Suelo		.dibujar(entorno, suelo);
			//Suelo		.mover(suelo);
			moverFondo  .dibujar(entorno, movFondo);
			moverFondo  .mover(movFondo);
			
			musicaJuego	.start();
			princesa	.dibujarPersonaje(entorno);
			princesa	.impulso();
			Obstaculo	.dibujar(entorno, obstaculos);
			Obstaculo	.mover(obstaculos);
			Soldado		.dibujar(entorno, soldados);
			Soldado		.mover(soldados);
			Soldado		.spam(soldados);
			Fireball	.dibujar(entorno, fireballs);
			Fireball	.mover(fireballs);
			mostrarPuntos();
			mostrarVidas();
		
			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				//saltar.start();
				princesa.setSalto(true);
				
			}
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				princesa.avanzar();	
			}
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				princesa.retroceder();
			}
			if (princesa.siChoca(obstaculos, soldados) && princesa.esVulnerable()) {
				princesa.setVulnerable(false);
				vidas--;
			}
			if (!princesa.siChoca(obstaculos, soldados)){
				princesa.setVulnerable(true);
			}
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				princesa.disparar(fireballs);
				
			}
			for (int i = 0; i < fireballs.length; i++) {
				if (fireballs[i] != null) {
					if (fireballs[i].tocaSoldado(soldados)) {
						puntos += 5;
						fireballs[i] = null;
					}
				}
				if (fireballs[i] != null) {
					if (fireballs[i].tocaObstaculo(obstaculos) || fireballs[i].getX() > 800) {
						fireballs[i] = null;
					}
				}
			}
			
			if (vidas == 0) {
				terminado = true;
			}
			if (puntos >= 15) {
				terminado = true;
			}
		}
		if (terminado && vidas == 0) {
			mostrarGameOver();
		}
		if (terminado && puntos >= 15) {
			mostrarVictoria();
		}
	}
	//	-----------------------------------------------------------------------------------
	public void mostrarPuntos() {
		entorno.cambiarFont("Comic Sans MS", 40, Color.RED);	// demasiadas lineas
		entorno.escribirTexto("Points: " + puntos, 600, 100);	// se podrian meter en un metodo
		
	
		
		//----------------MUESTRO LOS PTS PARA GANAR AHORA----------------//
		entorno.cambiarFont("Comic Sans MS", 20, Color.ORANGE);	// demasiadas lineas
		entorno.escribirTexto("15pts para ganar/si perdes sale otra ventana", 100, 300);	// se podrian meter en un metodo
	}
	
	
	
	public void mostrarVidas() {
		entorno.cambiarFont("Comic Sans MS", 40, Color.GREEN);
		entorno.escribirTexto("Lifes: " + vidas, 30, 100);
	}
	
	
	public void mostrarGameOver() {
		musicaJuego.close();
		musicaGO.start();
		fondo.dibujarGO(entorno);
		princesa.dibujarPerdio(entorno); 
		entorno.cambiarFont("Arial", 30, Color.white);
        entorno.escribirTexto("Press ENTER  to play",10,500 );
		
		if(entorno.sePresiono(entorno.TECLA_ENTER)) {
			musicaGO.close();
			j = new Juego();
		}
	}
	private void mostrarVictoria() {
		musicaJuego.close();
		fondo.dibujar(entorno);
		fondo.dibujarNombreGanaste(entorno);
		princesa.dibujarGano(entorno);
		princesa.dibujarGato(entorno);
		entorno.cambiarFont("Comic Sans MS", 26, Color.MAGENTA);
		entorno.escribirTexto("Final Score: " + puntos, 500, 400);
		// TODO cuando puntos >= 100, rescata al gato
	}
//	-----------------------------------------------------------------------------------


}
