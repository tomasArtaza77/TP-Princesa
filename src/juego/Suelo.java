/*
package juego;

import java.awt.Image;
import entorno.Entorno;

public class Suelo {
	private double x;
	private double y;
	private Image img;

	public Suelo(double x) {
		super();
		this.x = x;
		this.y = 550;
		img = entorno.Herramientas.cargarImagen("imagenes/pasto1.png");
	}
	
	public static Suelo[] inicializar() {
		Suelo[] s = new Suelo[3];
		s[0] = new Suelo(200);
		s[1] = new Suelo(600);
		s[2] = new Suelo(1000);
		return s;
	}
	
	public static void dibujar(Entorno entorno, Suelo[] s) {
		for (int i = 0; i < s.length; i++) {
			entorno.dibujarImagen(s[i].img, s[i].x, s[i].y, 0);
		}
	}
	
	public static void mover(Suelo[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i].x -= 1.5;
			if (s[i].x < -200) {
				s[i].x = 1000;
			}
		}
	}
	
}
*/