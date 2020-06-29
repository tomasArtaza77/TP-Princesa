package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;

public class GameOver extends InterfaceJuego {
	private Entorno go;
	private Fondo fondo;
	private Clip musicaGO;
	
	GameOver() {
		go = new Entorno(this, "Game Over", 800, 600);
		fondo = new Fondo();
		musicaGO = Herramientas.cargarSonido("musica/WIL.wav");
		go.iniciar();
	}
	
	public void tick() {
		musicaGO.start();
		fondo.dibujarMenu(go);
		fondo.dibujarNombreJuego(go);
		go.cambiarFont("Arial", 80, Color.WHITE);
		go.escribirTexto("Game Over", 250, 470);

	}
}
