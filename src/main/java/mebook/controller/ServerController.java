package mebook.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import mebook.exception.ServerQueryException;
import mebook.model.BookDAO;
import mebook.model.BookInfo;

@Controller
public class ServerController extends ServicesController {

	@Autowired
	public BookDAO bookDao;
	private static final Logger LOGGER = Logger.getLogger(ServerController.class);

	/**
	 * 모든 책 정보 목록을 반환한다.
	 * 
	 * @param request
	 *            HttpServletRequest 객체
	 * @param response
	 *            HttpServletResponse 객체
	 * @return 모든 책 정보 목록
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public void getBookList(HttpServletRequest request, HttpServletResponse response) {

		List<Map<String, String>> result = bookDao.getBookList();
		LOGGER.info("책 정보 목록을 가져왔습니다."); //$NON-NLS-1$

		String bookInfoList = new Gson().toJson(result);
		sendResponse(response, HttpServletResponse.SC_OK, bookInfoList);
	}

	/**
	 * 특정 책 정보를 삭제한다.
	 * 
	 * @param response
	 *            HttpServletResponse 객체
	 * @param id
	 *            삭제할 책 ID
	 */
	@RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE, produces = Produces.APPLICATION_JSON)
	public void deleteBookInfo(HttpServletResponse response, @PathVariable int id) {

		bookDao.deleteBook(id);
		LOGGER.info(String.format("책 정보(ID: %d)를 제거하였습니다.", id)); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}

	/**
	 * 새로운 책 정보를 등록한다.
	 * 
	 * @param response
	 *            HttpServletResponse 객체
	 * @param bookInfo
	 *            등록할 책 정보
	 */
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = Produces.APPLICATION_JSON)
	public void addBookInfo(HttpServletResponse response, @RequestBody BookInfo bookInfo) {

		try {
			bookDao.insertBook(bookInfo);

		} catch (ServerQueryException e) {
			e.printStackTrace();
			sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}

		LOGGER.info(String.format("\"%s\" 책 정보를 등록하였습니다.", bookInfo.getTitle())); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}

	/**
	 * 특정 책 정보를 수정한다.
	 * 
	 * @param response
	 *            HttpServletResponse 객체
	 * @param bookInfo
	 *            수정할 책 정보
	 * @param id
	 *            수정할 책 ID
	 */
	@RequestMapping(value = "/books/{id}", method = RequestMethod.PUT, produces = Produces.APPLICATION_JSON)
	public void editBookInfo(HttpServletResponse response, @PathVariable int id, @RequestBody BookInfo bookInfo) {
		
		bookInfo.setId(id);
		
		try {
			bookDao.updateBook(bookInfo);

		} catch (ServerQueryException e) {
			e.printStackTrace();
			sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}

		LOGGER.info(String.format("\"%s\" 책 정보를 등록하였습니다.", bookInfo.getTitle())); //$NON-NLS-1$

		sendResponse(response, HttpServletResponse.SC_OK);
	}
}
