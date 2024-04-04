package kr.com.ezen.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//메인함수없이 테스트를 실행하고 싶을때 어노테이션
	@Test
	public void testConnection() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"c##test2",
					"1234"
					);
			log.info(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
