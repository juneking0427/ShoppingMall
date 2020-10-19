package com.app.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.commonVo.FileVo;
import com.app.util.MybatisClient;



@Service
public class FileDao   {
	private static FileDao dao;
		
	

	private FileDao() {
	}

	public static synchronized FileDao getInstance() {
		if (dao == null){
			dao = new FileDao();
		}
		return dao;
	}
	
	
	
	// Message File 입력
	public int  insertFile (SqlSession sqlMapper, FileVo vo)throws Exception{
		return sqlMapper.insert("com.app.common.mapper.FileMapper.insertFile", vo);
	}
		
	// Message File 수정
	public int  updateFile (SqlSession sqlMapper, FileVo vo)throws Exception{
		return sqlMapper.update("com.app.common.mapper.FileMapper.updateFile", vo);
	}
	
	// Message File 삭제
	public int  deleteFile (SqlSession sqlMapper, String file_id)throws Exception{
		return sqlMapper.update("com.app.common.mapper.FileMapper.deleteFile", file_id);
	}
	
}