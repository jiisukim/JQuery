package JDBCUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 	db.properties 파일의 내용으로 DB정보를 설정하는 방법
 	방법)1 properties 객체 이용하기
 */
public class JDBCUtil2 {

	static Properties prop; // Properties 객체 변수 선언
	
	static {
		
		prop = new Properties(); //객체 생성
		
		try {
			FileInputStream fis = new FileInputStream("res/db.properties" );
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
			
			
		}catch(IOException ex) {
			System.out.println("팔이이 없거나 입출력 오류");
			ex.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
//DB연결

public static Connection getConnection() {
	
	try {
		
		return DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("username"),
				prop.getProperty("password"));
				
	}catch(SQLException ex){
		System.out.println("DB 연결 실패!!!");
		ex.printStackTrace();
		return null;
	}
}
}