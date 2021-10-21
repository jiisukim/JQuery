package JDBCUtil;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 	sqlmapclient 객체를 제공하는 팩토리 클래스
 * @author PC-18
 *
 */

public class SqlMapClientFactory {
	private static SqlMapClient smc;	// SqlMapClient객체 변수 선언
	
	public static SqlMapClient getInstance() {
		if (smc == null) {
			try {
				// 1-1 . xml문서 읽어오기
				Charset charset = Charset.forName("UTF-8");
				Resources.setCharset(charset);

				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");

				// 1-2. 위에서 일겅온 Reader 객체를 이용하여 실제 작업을 진행할 객체 생성
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);

				rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return smc;
	}
}
