package project.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import project.model.CYMemberData;

public class CyMemberService {

	private String dbID = "CY";
	private String dbPW = "cyworld1";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	

	// 회원가입 정보 DB 저장
	public void insertMember(String userid, String userpw, String email, String username, String phoneNo,
			String address, String gender) {
		
		String sql = "INSERT INTO cy_member (userid, userpw, email ,username, phoneNo, address, gender, wdate)"
				+ "VALUES(?,?,?,?,?,?,?,SYSDATE)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, userpw);
			pst.setString(3, email);
			pst.setString(4, username);
			pst.setString(5, phoneNo);
			pst.setString(6, address);
			pst.setString(7, gender);

			int result = pst.executeUpdate();

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

	// 로그인 확인 및 정보 확인
	public String memberChk(String userID, String userPW) {

		String memberChk = null;

		String sql = "";
		sql = "SELECT * FROM cy_member WHERE userid = " + "'" + userID + "'";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			boolean isLogin = false;
			while (rs.next()) {
				if (userPW.equals(rs.getString("userpw"))) {
					isLogin = true;
					int idx = rs.getInt("idx");
					String username = rs.getString("username");
					memberChk = idx + "," + username;

				} else {
					System.out.println("비밀번호 불일치");
				}

			}
			if (isLogin) {
				memberChk = memberChk;
//					System.out.println("로그인");

			} else {
				memberChk = "not user";
//					System.out.println("로그인 실패");
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
		return memberChk;

	}

	// 유저의 정보 받아오기
	public int getUserIdx(int h_idx) {

		int userIdx = 0;

		String sql = "select m.idx, m.username from cy_minihp h "
				+ "left outer join cy_member m on h.m_idx = m.idx "
				+ "where h.idx ="+ h_idx;

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				
				userIdx = rs.getInt("idx");
				String username = rs.getString("username");
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userIdx;

	}

}
