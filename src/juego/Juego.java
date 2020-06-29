package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;
	
public class Juego extends InterfaceJuego {
	private Entorno		entorno;
	private PrincesaPikachu princesaPika;
	private Juego 		j;
	private Fireball[]	fireballs;
	private Obstaculo[] obstaculos;
	private Soldado[] 	soldados;
	private Fondo 		fondo;
	private Escenario[] escenario;
	private int 		puntos;
	private int 		vidas;
	private boolean		empezo;
	private boolean		gano;
	private boolean		perdio;
	private Clip 		musicaGO;
	private Clip 		musicaJuego;
	
	public Juego() {
		entorno  	  = new Entorno(this, "Super Elizabeth Sis", 800, 600);
		fondo 		  = new Fondo();
		princesaPika  = new PrincesaPikachu();
		fireballs	  = new Fireball[10];
		musicaGO	  = Herramientas.cargarSonido("musica/perdio.wav");
		musicaJuego   = Herramientas.cargarSonido("musica/musicaJuego.wav");
		escenario	  = Escenario.inicializarEscenario();
		obstaculos	  = Obstaculo.inicializar();
		soldados	  = Soldado.inicializar();
		puntos		  = 0;
		vidas		  = 3;
		empezo		  = true;
		gano		  = false;
		perdio		  = false;
		entorno.iniciar();
	}
	 
	public void tick() {
		if (empezo) {
			musicaJuego	.start();
			fondo		.dibujarFondoGano(entorno);
			Escenario 	.dibujarPaisaje(entorno, escenario);
			Escenario 	.moverPaisaje(escenario);
			Escenario	.dibujarSuelo(entorno, escenario);
			Escenario	.moverSuelo(escenario);
			princesaPika.dibujarPersonaje(entorno);
			princesaPika.impulso();
			Obstaculo	.dibujar(entorno, obstaculos);
			Obstaculo	.mover(obstaculos);
			Soldado		.dibujar(entorno, soldados);
			Soldado		.mover(soldados);
			Soldado		.reaparece(soldados);
			Soldado		.soldadoChocaObstaculo(soldados, obstaculos);
			Fireball	.dibujar(entorno, fireballs);
			Fireball	.mover(fireballs);
			Fondo		.mostrarPuntos(entorno, puntos);
			Fondo		.mostrarVidas(entorno, vidas);
		
			if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				princesaPika.setSalto(true);
				
			}
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				princesaPika.avanzar();	
			}
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				princesaPika.retroceder();
			}
			if (princesaPika.siChoca(obstaculos, soldados) && princesaPika.esVulnerable()) {
				princesaPika.setVulnerable(false);
				vidas--;
			}
			if (!princesaPika.siChoca(obstaculos, soldados)){
				princesaPika.setVulnerable(true);
			}
			if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				princesaPika.disparar(fireballs);
				
			}
			for (int i = 0; i < fireballs.length; i++) {
				if (fireballs[i] != null && fireballs[i].tocaSoldado(soldados)) {
					puntos += 5;
					fireballs[i] = null;
				}
				if (	fireballs[i] != null &&
						(fireballs[i].tocaObstaculo(obstaculos) || fireballs[i].getX() > 800) ) {
					fireballs[i] = null;
				}
			}
			if (vidas == 0) {
				perdio = true;
				empezo = false;
			}
			if (puntos >= 15) {
				gano = true;
				empezo = false;
			}
		}
		if (gano) {
			Fondo.mostrarVictoria(entorno, fondo, princesaPika, musicaJuego, musicaGO, puntos, j);
		}
		if (perdio) {
			Fondo.mostrarGameOver(entorno, fondo, princesaPika, musicaJuego, musicaGO, j);
		}
	}
		
}
