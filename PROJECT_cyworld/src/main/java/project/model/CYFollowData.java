package project.model;

public class CYFollowData  extends CYShareData{
	
	private int m_idx_2;
	private String username_2;
	private int status;
	private int f_idx;
	private int h_idx;

	public CYFollowData(String userID, String username, int idx, int m_idx_2, int f_idx, int h_idx, String username_2, int status) {
		super(userID, username, idx);
		
		this.m_idx_2 = m_idx_2;
		this.f_idx = f_idx;
		this.h_idx = h_idx;
		this.username_2 = username_2;
		this.status = status;
	}

	
	public int getM_idx_2() {
		return m_idx_2;
	}



	public void setM_idx_2(int m_idx_2) {
		this.m_idx_2 = m_idx_2;
	}



	public int getH_idx() {
		return h_idx;
	}



	public void setH_idx(int h_idx) {
		this.h_idx = h_idx;
	}



	public int getF_idx() {
		return f_idx;
	}


	public void setF_idx(int f_idx) {
		this.f_idx = f_idx;
	}


	public String getUsername_2() {
		return username_2;
	}

	public void setUsername_2(String username_2) {
		this.username_2 = username_2;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
