package kr.or.ddit.common.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.dao.ZipDao;
import kr.or.ddit.common.vo.ZipVO;

public class ZipService {

	private ZipDao zipDao;
	
	public ZipService() {
		if(zipDao == null) zipDao = new ZipDao();
	}
	
	public List<ZipVO> retrieveZipList(String dong) {
		List<ZipVO> list = new ArrayList<ZipVO>();
		try {
			list = zipDao.retrieveZipListByDong(dong);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
