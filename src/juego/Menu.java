package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;

@SuppressWarnings("unused")
public class Menu extends InterfaceJuego {
	private Entorno app;
	private Fondo fondo;
//	private Fondo nombre;
	private Clip musicaMenu;
	
	Menu() {
		app = new Entorno(this, "Super Elizabeth Sis", 800, 600);
		fondo = new Fondo();
//		nombre = new Fondo();
		musicaMenu = Herramientas.cargarSonido("musica/musicaM.wav");
		app.iniciar();
	}
	
	public void tick() {
		musicaMenu.start();
		fondo.dibujarMenu(app);
		fondo.dibujarNombreJuego(app);
		app.cambiarFont("Arial", 30, Color.WHITE);
		app.escribirTexto("Press SPACE to play", 250, 470);
		
		if (app.sePresiono(app.TECLA_ESPACIO)) {
			musicaMenu.close();
			app.removeNotify();
			Juego juego = new Juego();
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
	}
}
