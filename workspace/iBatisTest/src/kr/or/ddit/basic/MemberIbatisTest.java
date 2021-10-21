package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVo;

public class MemberIbatisTest {
	public static void main(String[] args) {
		// ibatis를 이용하여 DB자료를 처리하는 작업순서
		// 1. iBatis의 환경설정 파일을 읽어와 실행시킨다.
		try {
			//1-1 . xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			//1-2. 위에서 일겅온 Reader 객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader 객체닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			//2-1. insert작업 연습
			System.out.println("insert 작업시작....");
			
			//1) 저장할 데이터를 VO에 담는다.
			MemberVo mv = new MemberVo();
			mv.setMemId("d001");
			mv.setMemName("강감찬");
			mv.setMemTel("010-1111-1111");
			mv.setMemAddr("경상남도 진주시");
			
			//2) SqlMapClient 객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값",파라미터클래스)
			//		반환값 : 성공하면 null이 반환된다.
			
//			Object obj = smc.insert("memberTest.insertMember", mv);
//			if(obj == null) {
//				System.out.println("insert작업 성공!");
//			}else {
//				System.out.println("insert작업 실패!!");
//			}
//			System.out.println("-------------------------------------------------");
			
			//2-2 update 연습
			System.out.println("update 작업 시작...");
			MemberVo mv2 = new MemberVo();
			mv2.setMemId("d001");
			mv2.setMemName("이순신");
			mv2.setMemTel("010-3333-3333");
			mv2.setMemAddr("서울시 영등포구");
			
			//update()메서드의 반환값은 성공한 레코드 수이다.
			int cnt = smc.update("memberTest.updateMember",mv2);
			
			if(cnt>0) {
				System.out.println("update작업성공");
			}else {
				System.out.println("update 작업 실패 !!!");
			}
			System.out.println("----------------------------");
			
			// 2-3 delete 연습
			System.out.println("delete 작업시작...");
			//delete 메서드의 반환값 : 성공한 레코드 수
			
			int cnt2 = smc.delete("memberTest.deleteMember","d001");
			if(cnt2 > 0) {
				System.out.println("delete 작업성공 !!");
			}else {
				System.out.println("delete 작업 실패...");
			}
			
			// 2-4. select 연습
			// 1) 응답의 결과가 여러개일 경우
//			System.out.println("select 연습시작(결과가 여러개 일 경우....)");
//			
//			//응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다.
//			// 이 메서드는 여러개의 레코드를 Vo에 담은 후에 이 Vo데이터를 List에 추가해 주는 작업을 자동으로
//			// 수행한다.
//			List<MemberVo> memList = smc.queryForList("memberTest.getMemberAll");
//			
//			for(MemberVo vo: memList ) {
//				System.out.println("ID : " + vo.getMemId());
//				System.out.println("이름 : " + vo.getMemName());
//				System.out.println("전화 : " + vo.getMemTel());
//				System.out.println("주소 : " + vo.getMemAddr());
//				System.out.println("==========================");
//			}
//			System.out.println("출력 끝.....");
				
			//2) 응답이 한개일 경우
			System.out.println("select연습 시작(결과가 1개일 경우)...");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject라는 메서드를 사용한다.
			MemberVo mv3 = (MemberVo) smc.queryForObject("memberTest.getMember","jisu");
			System.out.println("ID : " + mv3.getMemId());
			System.out.println("이름 : " + mv3.getMemName());
			System.out.println("전화 : " + mv3.getMemTel());
			System.out.println("주소 : " + mv3.getMemAddr());
			System.out.println("==========================");
			
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
