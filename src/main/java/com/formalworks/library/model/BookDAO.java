/**
 * FileName : ProjectGroupDAO.java
 * Created  : 2015. 9. 15.
 * Author   : JUNG UK LEE
 * Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
 *
 * ��臾몄꽌��紐⑤뱺 ��옉沅�諛�吏�쟻 �ъ궛沅뚯� (二��щ��띿뒪�먭쾶 �덉뒿�덈떎.
 * ��臾몄꽌���대뼚��遺�텇���덇� �놁씠 蹂듭젣 �먮뒗 �섏젙 �섍굅�� �꾩넚�����놁뒿�덈떎.
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

	private static final String TEXT_BOOK_TITLE_NOT_EXIST = "도서 제목이 입력되지 않았습니다.";

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

	public List<HashMap<String, String>> getBookList() {

		return sqlSession.selectList("BookManagement.selectBooks"); //$NON-NLS-1$
	}

	public int deleteBook(@Param("bookID") String bookID) {

		return sqlSession.delete("BookManagement.deleteBook", bookID); //$NON-NLS-1$
	}

	/**
	 * DB��梨낆쓣 �깅줉�쒕떎.
	 * 
	 * @param bookInfo
	 * @throws ServerQueryException
	 */
	public int insertBook(BookInfo bookInfo) throws ServerQueryException {

		String callNo = bookInfo.getCallNo();
		String reqNo = bookInfo.getRegNo();
		String bookTitle = bookInfo.getBookTitle();
		String author = bookInfo.getAuthor();
		String publisher = bookInfo.getPublisher();
		String description = bookInfo.getDescription();
		String imagePath = bookInfo.getImagePath();
		String originLocation = bookInfo.getOriginLocation();
		String currentLocation = bookInfo.getCurrentLocation();
		String isbn = bookInfo.getIsbn();

		if (bookTitle == null) {
			LOGGER.error(TEXT_BOOK_TITLE_NOT_EXIST);
			throw new ServerQueryException(TEXT_BOOK_TITLE_NOT_EXIST,
					HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("callNo", callNo); //$NON-NLS-1$
		map.put("regNo", reqNo); //$NON-NLS-1$
		map.put("author", author); //$NON-NLS-1$
		map.put("publisher", publisher); //$NON-NLS-1$
		map.put("bookTitle", bookTitle); //$NON-NLS-1$
		map.put("description", description); //$NON-NLS-1$
		map.put("imagePath", imagePath); //$NON-NLS-1$
		map.put("originLocation", originLocation); //$NON-NLS-1$
		map.put("currnetLocation", currentLocation); //$NON-NLS-1$
		map.put("isbn", isbn); //$NON-NLS-1$

		return sqlSession.insert("BookManagement.insertBook", map); //$NON-NLS-1$
	}

	public int updateBook(String id, BookInfo bookInfo)
			throws ServerQueryException {

		String callNo = bookInfo.getCallNo();
		String reqNo = bookInfo.getRegNo();
		String bookTitle = bookInfo.getBookTitle();
		String author = bookInfo.getAuthor();
		String publisher = bookInfo.getPublisher();
		String description = bookInfo.getDescription();
		String imagePath = bookInfo.getImagePath();
		String originLocation = bookInfo.getOriginLocation();
		String currentLocation = bookInfo.getCurrentLocation();
		String isbn = bookInfo.getIsbn();

		if (bookTitle == null) {
			LOGGER.error(TEXT_BOOK_TITLE_NOT_EXIST);
			throw new ServerQueryException(TEXT_BOOK_TITLE_NOT_EXIST,
					HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("callNo", callNo); //$NON-NLS-1$
		map.put("regNo", reqNo); //$NON-NLS-1$
		map.put("bookID", id); //$NON-NLS-1$
		map.put("author", author); //$NON-NLS-1$
		map.put("publisher", publisher); //$NON-NLS-1$
		map.put("bookTitle", bookTitle); //$NON-NLS-1$
		map.put("description", description); //$NON-NLS-1$
		map.put("imagePath", imagePath); //$NON-NLS-1$
		map.put("originLocation", originLocation); //$NON-NLS-1$
		map.put("currnetLocation", currentLocation); //$NON-NLS-1$
		map.put("isbn", isbn); //$NON-NLS-1$

		return sqlSession.update("BookManagement.updateBook", map); //$NON-NLS-1$
	}
}
