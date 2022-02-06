package project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import project.model.CYMiniHPData;
import project.model.CYFollowData;
import project.model.CYMemberData;

public class CyMiniHpService {

	private String dbID = "CY";
	private String dbPW = "cyworld1";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String driver = "oracle.jdbc.driver.OracleDriver";

	// 미니홈피 만들기
	public void createMiniHP(String intro1, String intro2, String domain, int m_idx) {

		String sql = "INSERT INTO cy_miniHP (m_idx, today, domain , intro1, intro2, imgs)" + "VALUES(?,?,?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, m_idx);
			pst.setInt(2, 0);
			pst.setString(3, domain);
			pst.setString(4, intro1);
			pst.setString(5, intro2);
			pst.setString(6, null);

			int result = pst.executeUpdate();
			System.out.println("createHP:" + result);

			con.close();
			pst.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 미니홈피 데이터 담기
	public CYMiniHPData minihpData_member(int idx) {
		CYMiniHPData minihpData;
		minihpData = null;

//		String sql1 ="SELECT * FROM cy_miniHP WHERE m_idx="+ idx;

		String sql2 = "SELECT m.userid, m.username, h.*"
				+ " FROM cy_member m right join cy_miniHP h on m.idx = h.m_idx " + "WHERE h.m_idx =" + idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			while (rs.next()) {

				int idx2 = rs.getInt("m_idx");
				idx = rs.getInt("idx");
				String userID = rs.getString("userid");
				String username = rs.getString("username");
				String intro1 = rs.getString("intro1");
				String intro2 = rs.getString("intro2");
				String domain = rs.getString("domain");
				String imgs = rs.getString("imgs");
				int today = rs.getInt("today");

				minihpData = new CYMiniHPData(userID, username, idx, idx2, intro1, intro2, domain, imgs, today);
			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return minihpData;
	}

	public CYMiniHPData minihpData_minihp(int idx) {

		CYMiniHPData minihpData;
		minihpData = null;

		String sql2 = "SELECT m.userid, m.username, h.*"
				+ " FROM cy_member m right join cy_miniHP h on m.idx = h.m_idx " + "WHERE h.idx =" + idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			while (rs.next()) {

				int idx2 = rs.getInt("m_idx");
				idx = rs.getInt("idx");
				String userID = rs.getString("userid");
				String username = rs.getString("username");
				String intro1 = rs.getString("intro1");
				String intro2 = rs.getString("intro2");
				String domain = rs.getString("domain");
				String imgs = rs.getString("imgs");
				int today = rs.getInt("today");

				minihpData = new CYMiniHPData(userID, username, idx, idx2, intro1, intro2, domain, imgs, today);
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return minihpData;

	}

	// 미니홈피 방명록 작성하기
	public void writeVisitlog(int m_idx, int h_idx, String visitContent, int status) {

		String sql = "INSERT INTO cy_visitorbook (m_idx, h_idx, status, visitcontent, wdate ) "
				+ "		VALUES(?,?,?,?,SYSDATE)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, m_idx);
			pst.setInt(2, h_idx);
			pst.setInt(3, status);
			pst.setString(4, visitContent);

			int result = pst.executeUpdate();
			System.out.println("addVisitLog:" + result);

			con.close();
			pst.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 방명록 글 보여지기
	public ArrayList<CYMiniHPData> getVisitLog(int m_idx, int idx) {
		ArrayList<CYMiniHPData> visitArr = new ArrayList<CYMiniHPData>();

		int curIdx = m_idx;

		// sql문을 JOIN 통해서 하나로 만들기
		String sql2 = "";

		sql2 = "SELECT cy_member.userid, cy_member.username, t.* FROM " + "      ("
				+ "         SELECT v.m_idx, v.idx, v.h_idx, v.visitcontent, v.status, v.wdate "
				+ "         FROM cy_member m RIGHT OUTER JOIN cy_visitorbook v "
				+ "         ON m.idx = v.m_idx order by idx desc "
				+ "        )t RIGHT OUTER JOIN cy_member ON t.m_idx = cy_member.idx " + " where t.h_idx = " + idx;

		String sql3 = "SELECT h.idx hidx, t.* FROM " + "      ("
				+ "         SELECT m.userid, m.username, v.m_idx, v.idx, v.h_idx ,v.visitcontent, v.status, v.wdate "
				+ "         FROM cy_member m RIGHT OUTER JOIN cy_visitorbook v "
				+ "         ON m.idx = v.m_idx order by idx desc "
				+ "        )t RIGHT OUTER JOIN cy_minihp h ON t.m_idx = h.m_idx " + " where t.h_idx = " + idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql3);

			CYMiniHPData visitLog = null;

			while (rs.next()) {
				// model(BoardData)에서 만든 생성자에 관해 idx, 타이틀, 유저네임, 불리언 생성 및 받아오기

				String userid = rs.getString("userid");
				String username = rs.getString("username");
				m_idx = rs.getInt("m_idx");
				int h_idx = rs.getInt("hidx");
				int v_idx = rs.getInt("idx");
				int status = rs.getInt("status");
				String visitContent = rs.getString("visitcontent");
				Date wdate = rs.getDate("wdate");

				boolean owenr = false;
				// 지금 로그인 된 idx 값과 MEMO_MEMBER idx 값이 같은지 확인
				if (curIdx == rs.getInt("m_idx")) {
					owenr = true;
				} else {
					owenr = false;
//					username = "guest";
				}
				// 받아온 데이터를 arraylist에 담기

				visitLog = new CYMiniHPData(userid, username, m_idx, v_idx, h_idx, status, visitContent, wdate, owenr);
				visitArr.add(visitLog);

			}
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return visitArr;
	}

	// 방명록 글 삭제
	public void deleteVisitLog(int v_idx) {

		String sql = "delete from cy_visitorbook where idx =" + v_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);

			System.out.println("visitlog delete :" + result);
			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 홈피 투데이 수 담기
	public int countToday(int idx) {
		int countToday = 0;

		String sql = "select today from cy_minihp where m_idx =" + idx;
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				countToday = rs.getInt("today");
			}
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return countToday;
	}

	// 투데이 업데이트
	public void updateToday(int curCountToday, int v_m_idx) {

		String sql = "UPDATE cy_minihp h set h.today = " + curCountToday + "WHERE h.m_idx =" + v_m_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);

//			System.out.println("update today :" + result);

			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 이름으로 검색 후 친구 목록 보여지기
	public ArrayList<CYMiniHPData> getFindList(String username) {
		ArrayList<CYMiniHPData> memArr = new ArrayList<CYMiniHPData>();

		String sql = "select sub1.username, sub1.gender, sub1.userid, sub1.email, h.idx, h.domain from "
				+ "(select m.idx, m.username, m.gender, m.userid, m.email from cy_member m)sub1 "
				+ "left outer join cy_minihp h on h.m_idx = sub1.idx " + "where sub1.username =" + "'" + username + "'";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			CYMiniHPData findFriend = null;

			int h_idx = 0;
			int m_idx = 0;
			String find_username = "";
			String userid = "";
			String gender = "";
			String email = "";
			String domain = "";

			while (rs.next()) {

				h_idx = rs.getInt("idx");
				find_username = rs.getString("username");
				userid = rs.getString("userid");
				gender = rs.getString("gender");
				email = rs.getString("email");
				domain = rs.getString("domain");

				findFriend = new CYMiniHPData(userid, find_username, h_idx, gender, email, domain);
				memArr.add(findFriend);

			}

			con.close();
			st.close();
			rs.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return memArr;
	}

	// 홈피 idx 값으로 홈피 주인 m_idx 가져오기-follow
	public int getUserIdx(int h_idx) {

		int userIdx = 0;

		String sql2 = "SELECT m.*" + " FROM cy_member m right join cy_miniHP h on m.idx = h.m_idx " + "WHERE h.idx ="
				+ h_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			while (rs.next()) {

				userIdx = rs.getInt("idx");
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userIdx;

	}// 멤버 idx 값으로 홈피 idx 가져오기

	public int getMiniHpIdx(int m_idx) {

		int getMiniHpIdx = 0;

		String sql2 = "SELECT h.*" + " FROM cy_minihp h right join cy_member m on m.idx = h.m_idx " + "WHERE h.m_idx ="
				+ m_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			while (rs.next()) {

				getMiniHpIdx = rs.getInt("idx");
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return getMiniHpIdx;

	}

	// 일촌 맺기
	public void followCyUser(int m_idx_1, int m_idx_2, String username_1, String username_2) {

		String sql = "INSERT INTO cy_follow (m_idx_1, username_1, m_idx_2 , username_2, status)" + "VALUES(?,?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setInt(1, m_idx_1);
			pst.setString(2, username_1);
			pst.setInt(3, m_idx_2);
			pst.setString(4, username_2);
			pst.setInt(5, 1);

			int result = pst.executeUpdate();
			System.out.println("follow add:" + result);

			con.close();
			pst.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 로그인한 id와 홈피의 주인 idx로 해당 홈피 주인이 누굴 팔로우 했는지 보여주기
	public ArrayList<CYFollowData> getFollow(int m_idx_1, String userID) {

		ArrayList<CYFollowData> fArr = new ArrayList<CYFollowData>();

//		String sql = "select sub1.*, h.idx from "
//				+ "(select * from cy_follow order by idx desc)sub1 right outer join "
//				+ "cy_minihp h on sub1.m_idx_1 = h.m_idx where h.idx =" + h_idx;

		String sql2 = "select sub2.*, h.idx hidx from " + "    (" + "     select sub1.* from " + "       ( "
				+ "         select f.* from cy_follow f order by idx desc " + "        ) " + "        sub1 "
				+ "    )sub2 right outer join cy_minihp h on sub2.m_idx_2 = h.m_idx " + "where sub2.m_idx_1 = "
				+ m_idx_1;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			CYFollowData followData = null;

			while (rs.next()) {

				int f_idx = rs.getInt("idx");
				m_idx_1 = rs.getInt("m_idx_1");
				int m_idx_2 = rs.getInt("m_idx_2");
				int hidx = rs.getInt("hidx");
				String username_1 = rs.getString("username_1");
				String username_2 = rs.getString("username_2");
				int status = rs.getInt("status");

				followData = new CYFollowData(userID, username_1, m_idx_1, m_idx_2, f_idx, hidx, username_2, status);
				fArr.add(followData);
			}

			con.close();
			st.close();
			rs.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fArr;

	}

	// 일촌 데이터 담기
	public CYFollowData followData(int h_idx, String userID) {

		CYFollowData followData = null;

		String sql = "select sub1.* from " + "(select * from cy_follow order by idx desc)sub1 right outer join "
				+ "cy_minihp h on sub1.m_idx_1 = h.m_idx where h.idx =" + h_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				int f_idx = rs.getInt("idx");// 팔로우 idx
				int m_idx_1 = rs.getInt("m_idx_1");
				int m_idx_2 = rs.getInt("m_idx_2");
				String username_1 = rs.getString("username_1");
				String username_2 = rs.getString("username_2");
				int status = rs.getInt("status");

//				System.out.println(f_idx);

				followData = new CYFollowData(userID, username_1, m_idx_1, m_idx_2, f_idx, h_idx, username_2, status);
			}

			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return followData;

	}

	// 일촌에 포함 되어있는지 아닌지
	public boolean isFollow(int curIdx, int hp_midx) {

		boolean isFollow = false;
		String sql = "select * from cy_follow where m_idx_1 = '" + curIdx + "'";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				int m_idx_2 = rs.getInt("m_idx_2");

				if (hp_midx == m_idx_2) {
					isFollow = true;
					break;
				} else {
					isFollow = false;
				}

			}

			con.close();
			st.close();
			rs.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isFollow;

	}

	// 팔로우 idx 값 가져오기
	public int getFollowIdx(int curIdx, int m_idx) {

		int f_idx = 0;

		String sql = "select sub1.* from " + "(select * from cy_follow where m_idx_2 = " + m_idx + ")sub1 "
				+ "where m_idx_1 = " + curIdx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				f_idx = rs.getInt("idx");
			}

			con.close();
			rs.close();
			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return f_idx;
	}

	// 언팔하기
	public void unfollowUser(int f_idx) {

		String sql = "delete from cy_follow where idx = " + f_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);

			System.out.println("unfollow  :" + result);
			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 홈페이지 수정 기능
	public void editMiniHp(String intro1, String intro2, String imgs, String domain ,int h_idx) {

		String sql = "UPDATE cy_minihp h " + "set h.intro1 = '" + intro1 + "', h.intro2 = '" + intro2
				+ "' , h.domain = '" + domain + "', h.imgs = '"+imgs+"' WHERE h.idx =" + h_idx;
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);

			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
