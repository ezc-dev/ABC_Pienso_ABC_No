package application;

public class Jugador {
	
	private String points;
	private int vidas;
	
	
	public Jugador(String points, int vidas) {
		this.points = points;
		this.vidas = vidas;
	}


	public String getPoints() {
		return points;
	}


	public void setPoints(String points) {
		this.points = points;
	}


	public int getVidas() {
		return vidas;
	}


	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	
	
	
}
