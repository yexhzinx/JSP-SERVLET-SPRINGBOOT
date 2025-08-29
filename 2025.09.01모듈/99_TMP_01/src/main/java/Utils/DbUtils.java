package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dtos.MemberDto;


public class DbUtils {
	//속성(DB 연결관련된 멤버)
	//DB CONN DATA
	private static String id = "root";
	private static String pw = "1234";
	private static String url = "jdbc:mysql://localhost:3306/opendatadb";

	//JDBC참조변수
	private static Connection conn = null; // DBMS 의 특정 DB와 연결되는 객체
	private static PreparedStatement pstmt = null; // SQL Query 전송용 객체
	private static ResultSet rs = null; // Select 결과물 담을 객체
	
	//기능
	public static void conn() throws Exception
	{
		//드라이브 클래스 메모리 공간 적재
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loading Success...");
		//Connection conn 멤버에 Connection 객체 연결
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("DB CONNECTED...");
	}
	public static void disConn() throws Exception {
		//rs / pstmt / conn close 처리
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(conn!=null)
			conn.close();
	}	
	public static int insertMember(MemberDto memberDto) throws Exception 
	{
		//tbl_member 에 dto 값 저장 후 int 반환
		pstmt = conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pstmt.setString(1, memberDto.getUserid());
		pstmt.setString(2, memberDto.getPassword());
		pstmt.setString(3, memberDto.getUsername());
		pstmt.setString(4, memberDto.getZipcode());
		pstmt.setString(5, memberDto.getAddr1());
		pstmt.setString(6, memberDto.getAddr2());
		pstmt.setString(7, memberDto.getPhone1());
		pstmt.setString(8, memberDto.getPhone2());
		pstmt.setString(9, memberDto.getPhone3());
		pstmt.setString(10, memberDto.getTel1());
		pstmt.setString(11, memberDto.getTel2());
		pstmt.setString(12, memberDto.getTel3());
		pstmt.setString(13, memberDto.getEmail());
		pstmt.setString(14, memberDto.getYear());
		pstmt.setString(15, memberDto.getMonth());
		pstmt.setString(16, memberDto.getDay());
		int result = pstmt.executeUpdate();
		
		return result;
	}
	public static MemberDto selectMember(String userid) throws Exception{	
		//tbl_member 에 userid 와 일치하는 데이터 받아와 MemberDto로 반환  
		pstmt = conn.prepareStatement("select * from tbl_user where userid=?");
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		MemberDto dto = null;
		if(rs!=null && rs.next()) {
			dto = new MemberDto();
			dto.setUsername(rs.getString("userid"));
			dto.setPassword(rs.getString("password"));
			dto.setPassword(rs.getString("username"));
			dto.setPassword(rs.getString("zipcode"));
			dto.setPassword(rs.getString("addr1"));
			dto.setPassword(rs.getString("addr2"));
			dto.setPassword(rs.getString("phone1"));
			dto.setPassword(rs.getString("phone2"));
			dto.setPassword(rs.getString("phone3"));
			dto.setPassword(rs.getString("tel1"));
			dto.setPassword(rs.getString("tel2"));
			dto.setPassword(rs.getString("tel3"));
			dto.setPassword(rs.getString("email"));
			dto.setPassword(rs.getString("year"));
			dto.setPassword(rs.getString("month"));
			dto.setPassword(rs.getString("day"));
		}
		
		return dto;
	}
	
	public static void TxStart() throws Exception{
		if(conn!=null)
			conn.setAutoCommit(false);
	}
	public static void TxEnd() throws Exception {
		if(conn!=null)
			conn.commit();
	}
	public static void RollBack() throws Exception {
		if(conn!=null)
			conn.rollback();	
	}
	
}
