package fr.alamut.model;

public class Game {
	
	
	private String idGame;
	private String title;
	private String idIcon;
	private String status;
	
	
	public Game(String idGame, String title, String idIcon, String status) {
		this.idGame = idGame;
		this.title 	= title;
		this.idIcon = idIcon;
		this.status = status;
	}
	

	public String getIdGame() {
		return idGame;
	}


	public String getTitle() {
		return title;
	}


	public String getIdIcon() {
		return idIcon;
	}


	public String getStatus() {
		return status;
	}


	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setIdIcon(String idIcon) {
		this.idIcon = idIcon;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Game{" +
			    	"idGame='" + idGame + '\'' +
			    	", title='" + title + '\'' +
			    	", idIcon='" + idIcon + '\'' + 
			    	", status='" + status + '\'' + 
			    	'}';
	}
	
	

}
