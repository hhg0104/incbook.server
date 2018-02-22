/**
 * FileName : ProjectGroupDAO.java
 * Created  : 2015. 9. 15.
 * Author   : JUNG UK LEE
 * Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
 *
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.formalworks.library.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.formalworks.library.exception.ServerQueryException;
import com.mysql.jdbc.util.ServerController;

/**
 * 
 * @author JUNG UK LEE
 */
@Repository
@Component
public class BookDAO {

	private SqlSession sqlSession;

	private static final Logger LOGGER = Logger.getLogger(ServerController.class);

	/**
	 * @param sqlSession
	 *            the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * DB에서 책 정보 목록을 전부 가져온다.
	 * 
	 * @return 책 정보 목록
	 */
	public List<Map<String, String>> getBookList() {

		return sqlSession.selectList("BookManagement.selectBooks"); //$NON-NLS-1$
	}

	/**
	 * DB에서 특정 책을 제거한다.
	 * 
	 * @param id
	 *            책 ID
	 * @return 결과 Int
	 */
	public int deleteBook(int id) {

		return sqlSession.delete("BookManagement.deleteBook", id); //$NON-NLS-1$
	}

	/**
	 * DB에 책을 등록한다.
	 * 
	 * @param bookInfo
	 *            등록 정보
	 * @return 결과 Int
	 * @throws ServerQueryException
	 *             서버 쿼리 관련 예외사항
	 */
	public int insertBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "책 등록에 필요한 필수 정보가 입력되지 않았습니다."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.insert("BookManagement.insertBook", map); //$NON-NLS-1$
	}

	/**
	 * 책 정보를 수정한다.
	 * 
	 * @param bookInfo
	 *            수정 정보
	 * @return 결과 Int
	 * @throws ServerQueryException
	 *             서버 쿼리 관련 예외사항
	 */
	public int updateBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "책 등록에 필요한 필수 정보가 입력되지 않았습니다."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.update("BookManagement.updateBook", map); //$NON-NLS-1$
	}
}
