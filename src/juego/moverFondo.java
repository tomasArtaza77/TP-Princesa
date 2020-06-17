package juego;

import java.awt.Image;

import entorno.Entorno;

public class moverFondo {
	private double x;
	//private double y;
	private Image img;

	public moverFondo(double x) {
		super();
		this.x = x;
		//this.y = 550;
		img = entorno.Herramientas.cargarImagen("imagenes/bosque.gif");
	}
	
	public static moverFondo[] inicializar() {
		moverFondo[] s = new moverFondo[3];
		s[0] = new moverFondo(200);
		s[1] = new moverFondo(600);
		s[2] = new moverFondo(1000);
		return s;
	}
	
	public static void dibujar(Entorno entorno, moverFondo[] s) {
		for (int i = 0; i < s.length; i++) {
			//entorno.dibujarImagen(s[i].img, s[i].x, s[i].y, 0);
			entorno.dibujarImagen(s[i].img, s[i].x, entorno.alto()/2, 0.0, 2.25);
		}
	}
	
	public static void mover(moverFondo[] s) {
		for (int i = 0; i < s.length; i++) {
			s[i].x -= 1.5;
			if (s[i].x < -200) {
				s[i].x = 1000;
			}
		}
	}	
}
