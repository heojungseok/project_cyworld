package project.model;

public class CYShareData {
	
	protected String userID;
	protected String username;
	protected int idx;
	
	public CYShareData(String userID, String username, int idx) {
		this.userID = userID;
		this.username = username;
		this.idx = idx;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	

}
