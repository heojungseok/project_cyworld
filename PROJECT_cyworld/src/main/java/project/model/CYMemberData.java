package project.model;

import java.util.Date;

import project.service.InsertInfo;

public class CYMemberData extends CYShareData {

	private String userPW;
	private String gender;
	private String phoneNo;
	private String email;
	private Date wdate;

	//모든 정보를 불러오는 관리자 모드에서 사용하기
	public CYMemberData(String userID, String username, int idx, String userPW, String gender, String phoneNo,
			String email, Date wdate) {
		super(userID, username, idx);
		
		this.userPW = userPW;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.wdate = wdate;
	}
	

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}


}
