package juego;

import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import javax.sound.sampled.Clip;

public class Menu extends InterfaceJuego {
	private Entorno app;
	Fondo fondo;
	Fondo nombre;
	Clip musicaMenu;
	
	Menu() {
		app = new Entorno(this, "Super Elizabeth Sis", 800, 600);
		fondo = new Fondo();
		nombre = new Fondo();
		musicaMenu = Herramientas.cargarSonido("musica/musicaM.wav");
		
		app.iniciar();
	}
	
	@SuppressWarnings("unused")
	public void tick() {
		//Muestro la ventana del menu con sus chiches
		musicaMenu.start();
		fondo.dibujarMenu(app);
		fondo.dibujarNombreJuego(app);
		app.cambiarFont("Arial", 30, Color.WHITE);
		app.escribirTexto("Press SPACE to play", 250, 470);
		
		// Empieza el juego
		if (app.sePresiono(app.TECLA_ESPACIO)) {
			//no para la musica.EL stop NO SIRVE
			musicaMenu.close();
			Juego juego = new Juego();
			
		}

	}
	//Aca empieza
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Menu menu = new Menu();
	}

}