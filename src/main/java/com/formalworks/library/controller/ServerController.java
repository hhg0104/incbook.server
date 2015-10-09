package com.formalworks.library.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formalworks.library.exception.ServerQueryException;
import com.formalworks.library.model.BookInfo;
import com.formalworks.library.model.BookDAO;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ServerController extends ServicesController {

	@Autowired
	public BookDAO bookDao;
	private static final Logger LOGGER = Logger.getLogger(ServerController.class);

	/**
	 * Formalworks_Library의 책의 목록 및 정보를 반환한다.
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public void getBookList(HttpServletRequest request, HttpServletResponse response) {

		// 포멀웍스가 보유한 책의 목록 및 정보를 반환한다.
		List<HashMap<String, String>> result = bookDao.getBookList();
		LOGGER.info("Formalworks 도서 목록을 가져왔습니다."); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK, new Gson().toJson(result));
	}

	/**
	 * Formalworks_Library에 등록되어 있는 책을 제거한다.
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/books/{book_id}", method = RequestMethod.DELETE, produces = Produces.APPLICATION_JSON)
	public void deleteFormalworksBook(HttpServletResponse response,
			@PathVariable(BOOK_ID) String id) {

		// 특정 프로젝트 그룹을 삭제
		int deleteRows = bookDao.deleteBook(id);
		LOGGER.info(String.format("포멀웍스에서 책 %s개를 제거하였습니다.", deleteRows)); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}

	/**
	 * Formalworks_Library에 책을 등록한다.
	 * 
	 * @param response
	 * @param requestJson
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = Produces.APPLICATION_JSON)
	public void enrollBookInfo(HttpServletResponse response, @RequestBody String requestJson) {

		BookInfo bookInfo = BookInfo.fromJson(requestJson);

		// 책을 등록한다.
		int insertRows = 0;
		try {
			insertRows = bookDao.insertBook(bookInfo);
		} catch (ServerQueryException e) {
			e.printStackTrace();
			sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		LOGGER.info(String.format("Book_info 테이블에 책을 %s개 등록하였습니다.", insertRows)); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}

	/**
	 * Formalworks_Library에 책을 등록한다.
	 * 
	 * @param response
	 * @param bookID
	 */
	@RequestMapping(value = "/books/{book_id}", method = RequestMethod.PUT, produces = Produces.APPLICATION_JSON)
	public void enrollFormalworksBook(HttpServletResponse response, @RequestBody String requestJson,
			@PathVariable(BOOK_ID) String id) {

		BookInfo bookInfo = BookInfo.fromJson(requestJson);
		
		// 책의 위치 정보를 수정한다.
		try {
			bookDao.updateBook(id, bookInfo);
		} catch (ServerQueryException e) {
			e.printStackTrace();
			sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		LOGGER.info(String.format("formalworks_library  테이블에서 %s의 위치 및 대여 정보를 수정하였습니다.", id)); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}
}
