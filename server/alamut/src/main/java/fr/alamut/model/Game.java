package fr.alamut.model;



public class Game {
	
	

	private String title;
	private String idIcon;
	private String status;
	private String idOwnerPhoneNumber;
	
	public Game() {
	}
	
	public Game(String title, String idIcon, String status, String idOwnerPhoneNumber) {
		this.title 	= title;
		this.idIcon = idIcon;
		this.status = status;
		this.idOwnerPhoneNumber = idOwnerPhoneNumber;
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
	
	public String getIdOwnerPhoneNumber() {
		return idOwnerPhoneNumber;
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
	
	public void setIdOwnerPhoneNumber(String idOwnerPhoneNumber) {
		this.idOwnerPhoneNumber = idOwnerPhoneNumber;
	}


	@Override
	public String toString() {
		return "Game{" +
			    	", title='" + title + '\'' +
			    	", idIcon='" + idIcon + '\'' + 
			    	", status='" + status + '\'' +
			    	", idOwnerPhoneNumber='" + idOwnerPhoneNumber + '\'' +
			    	'}';
	}
	
	

}
