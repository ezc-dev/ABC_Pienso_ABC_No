package application;

public class Jugador {
	
	private int points;
	private int vidas;
	
	
	public Jugador(int points, int vidas) {
		this.points = points;
		this.vidas = vidas;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public int getVidas() {
		return vidas;
	}


	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	
	
	
}
