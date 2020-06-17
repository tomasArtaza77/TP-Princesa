package juego;

import java.awt.*;
import entorno.Herramientas;
import entorno.Entorno;
import java.util.Random;

public class Soldado {
	private double x;
	private double y;
	private double diam;
	private Image img;
	private double ang;

	Soldado(double x, double y, double diam) {
		this.x = x;
		this.y = y;
		this.diam = diam;
		this.ang = 0;
		this.img = Herramientas.cargarImagen("imagenes/enemigo 1.gif");
	}

	public static Soldado[] inicializar() {
		Soldado[] s = new Soldado[3];
		s[0] = new Soldado(1000, 500, 45);
		s[1] = new Soldado(1400, 500, 45);
		s[2] = new Soldado(1800, 500, 45);
		return s;
	}

	public static void dibujar(Entorno entorno, Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				entorno.dibujarImagen(s[i].img, s[i].x, s[i].y, s[i].ang, 0.3);
			}
		}
	}

	public static void mover(Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				s[i].mover();
			}
		}
	}

	public static void spam(Soldado[] s) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] == null) {
				s[i] = new Soldado(xRand(), 500, 45);
			}
			if (s[i] != null && s[i].x <= -22.5) {
				s[i] = new Soldado(xRand(), 500, 45);
			}
			if (seSolapa(s, s[i], i)) {
				s[i] = null;
			}
		}
	}

	private static boolean seSolapa(Soldado[] slds, Soldado sold, int j) {
		for (int i = 0; i < slds.length; i++) {
			if (sold != null && slds[i] != null && i != j) {
				double dist = Math.abs(sold.x - slds[i].x);
				if (dist < 500) {
					sold = null;
				}
			}
		}
		return false;
	}

	private static double xRand() {
		Random r = new Random();
		return 1000 + r.nextInt(1000);
	}

	private void mover() {
		this.x -= 2.0;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDiametro() {
		return diam;
	}

}
