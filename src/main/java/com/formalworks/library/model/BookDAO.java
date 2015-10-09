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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
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

	private static final Logger LOGGER = Logger
			.getLogger(ServerController.class);

	/**
	 * @param sqlSession
	 *            the sqlSession to set
	 */
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * DB에서 포멀웍스의 책 목록을 전부 가져온다.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getBookList() {

		return sqlSession.selectList("BookManagement.selectBooks"); //$NON-NLS-1$
	}

	/**
	 * DB에서 특정 책을 제거한다.
	 * 
	 * @param bookID
	 * @return
	 */
	public int deleteBook(@Param("bookID") String bookID) {

		return sqlSession.delete("BookManagement.deleteBook", bookID); //$NON-NLS-1$
	}

	/**
	 * DB에 책을 등록한다.
	 * 
	 * @param bookInfo
	 * @throws ServerQueryException
	 */
	public int insertBook(BookInfo bookInfo) throws ServerQueryException {

		String bookTitle = bookInfo.getBookTitle();
		String author = bookInfo.getAuthor();
		String publisher = bookInfo.getPublisher();
		String description = bookInfo.getDescription();
		String imagePath = bookInfo.getImagePath();
		String location = bookInfo.getLocation();
		String isbn = bookInfo.getIsbn();

		if (bookTitle == null) {
			String errMsg = "책 등록에 필요한 필수 정보가 입력되지 않았습니다."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg,
					HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("author", author); //$NON-NLS-1$
		map.put("publisher", publisher); //$NON-NLS-1$
		map.put("bookTitle", bookTitle); //$NON-NLS-1$
		map.put("description", description); //$NON-NLS-1$
		map.put("imagePath", imagePath); //$NON-NLS-1$
		map.put("location", location); //$NON-NLS-1$
		map.put("isbn", isbn); //$NON-NLS-1$

		return sqlSession.insert("BookManagement.insertBook", map); //$NON-NLS-1$
	}

	public int updateBook(String id, BookInfo bookInfo)
			throws ServerQueryException {

		String bookTitle = bookInfo.getBookTitle();
		String author = bookInfo.getAuthor();
		String publisher = bookInfo.getPublisher();
		String description = bookInfo.getDescription();
		String imagePath = bookInfo.getImagePath();
		String location = bookInfo.getLocation();
		String isbn = bookInfo.getIsbn();

		if (bookTitle == null) {
			String errMsg = "책 등록에 필요한 필수 정보가 입력되지 않았습니다."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg,
					HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("bookID", id); //$NON-NLS-1$
		map.put("author", author); //$NON-NLS-1$
		map.put("publisher", publisher); //$NON-NLS-1$
		map.put("bookTitle", bookTitle); //$NON-NLS-1$
		map.put("description", description); //$NON-NLS-1$
		map.put("imagePath", imagePath); //$NON-NLS-1$
		map.put("location", location); //$NON-NLS-1$
		map.put("isbn", isbn); //$NON-NLS-1$

		return sqlSession.update("BookManagement.updateBook", map); //$NON-NLS-1$
	}
}
