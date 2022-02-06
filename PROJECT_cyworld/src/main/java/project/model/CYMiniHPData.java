package project.model;

import java.util.Date;

public class CYMiniHPData extends CYShareData {

	private String intro1;
	private String intro2;
	private String domain;
	private String imgs;
	private int today;

	private boolean owner;
	private int status;
	private String visitContent;
	private Date wdate;
	private int idx2;
	private int idx3;

	private String email;
	private String gender;

	// 친구 찾기 위한 데이터
	public CYMiniHPData(String userID, String username, int idx,String gender, String email, String domain) {
		super(userID, username, idx);

		this.domain = domain;
		this.email = email;
		this.gender = gender;
	}

	// 방명록을 위한 데이터
	public CYMiniHPData(String userID, String username, int idx, int idx2, int idx3, int status, String visitContent, Date wdate,
			boolean owner) {
		super(userID, username, idx);
		
		this.idx2 = idx2;
		this.idx3 = idx3;
		this.status = status;
		this.visitContent = visitContent;
		this.owner = owner;
		this.wdate = wdate;

	}

	// 홈피를 위한 데이터
	public CYMiniHPData(String userID, String username, int idx, int idx2,String intro1, String intro2, String domain,
			String imgs, int today) {
		super(userID, username, idx);
		
		this.idx2 = idx2;
		this.intro1 = intro1;
		this.intro2 = intro2;
		this.domain = domain;
		this.imgs = imgs;
		this.today = today;

	}
	

	public int getIdx3() {
		return idx3;
	}

	public void setIdx3(int idx3) {
		this.idx3 = idx3;
	}

	public int getIdx2() {
		return idx2;
	}

	public void setIdx2(int idx2) {
		this.idx2 = idx2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public String getIntro1() {
		return intro1;
	}

	public void setIntro1(String intro1) {
		this.intro1 = intro1;
	}

	public String getIntro2() {
		return intro2;
	}

	public void setIntro2(String intro2) {
		this.intro2 = intro2;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public int getToday() {
		return today;
	}

	public void setToday(int today) {
		this.today = today;
	}

	public String getVisitContent() {
		return visitContent;
	}

	public void setVisitContent(String visitContent) {
		this.visitContent = visitContent;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
