package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import JDBCUtil.JDBCUtil;
import JDBCUtil.JDBCUtil3;
import JDBCUtil.SqlMapClientFactory;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVo;

public class MemberServiceImpl implements IMemberService{

	private static IMemberDao memDao;
	
//	private static IMemberService memService;
//	private MemberServiceImpl(){}
//	
//	public static IMemberService getInstance() {
//		if(memService == null) {
//			memService = new MemberServiceImpl();
//		}
//		return memService;
//	}
	private static IMemberService memService;
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	private SqlMapClient smc; 
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	@Override
	public int insertMember(MemberVo mv) {
		
		
		int cnt = 0;
		try {
			cnt =  memDao.insertMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt; 
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			memDao.deleteMember(smc,memId);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVo mv) {
		int cnt = 0;
		
		
		try {
			cnt = memDao.updateMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMemberList() {
		List<MemberVo> memList = new ArrayList<MemberVo>();

		
		try {
			memList = memDao.getAllMemberList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean chk = false;
		
		
		try {
			chk = memDao.getMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return chk;
	}

	@Override
	public List<MemberVo> getSearchMember(MemberVo mv) {
		List<MemberVo> memList = new ArrayList<>();
		
		
		try {
			memList = memDao.getSearchMember(smc, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return memList;
	}

}
