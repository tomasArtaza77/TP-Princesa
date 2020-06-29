package juego;

import java.awt.Image;
import entorno.Entorno;

public class Escenario {
	private double xPaisaje;
	private double xSuelo;
	private double ySuelo;
	private Image imgSuelo;
	private Image imgPaisaje;

	public Escenario(double xEscenario, double xSuelo) {
		this.imgPaisaje = entorno.Herramientas.cargarImagen("imagenes/bosque.gif");
		this.imgSuelo = entorno.Herramientas.cargarImagen("imagenes/pasto1.png");
		this.xPaisaje = xEscenario;
		this.xSuelo = xSuelo;
		this.ySuelo = 550;
	}
	
	public static Escenario[] inicializarEscenario() {
		Escenario[] s = new Escenario[3];
		s[0] = new Escenario(200, 200);
		s[1] = new Escenario(600, 600);
		s[2] = new Escenario(1000, 1000);
		return s;
	}
	
	public static void dibujarPaisaje(Entorno entorno, Escenario[] s) {
		for (int i = 0; i < s.length; i++) {
			entorno.dibujarImagen(s[i].imgPaisaje, s[i].xPaisaje, entorno.alto()/2, 0.0, 2.25);
		}
	}
	
	public static void moverPaisaje(Escenario[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i].xPaisaje -= 0.5;
			if (s[i].xPaisaje < -200) {
				s[i].xPaisaje = 1000;
			}
		}
	}	
	
	public static void dibujarSuelo(Entorno entorno, Escenario[] s) {
		for (int i = 0; i < s.length; i++) {
			entorno.dibujarImagen(s[i].imgSuelo, s[i].xSuelo, s[i].ySuelo, 0);
		}
	}
	
	public static void moverSuelo(Escenario[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i].xSuelo -= 1.5;
			if (s[i].xSuelo < -200) {
				s[i].xSuelo = 1000;
			}
		}
	}
}
