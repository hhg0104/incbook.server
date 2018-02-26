package mebook.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.util.ServerController;

import mebook.exception.ServerQueryException;

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
	 * DB?—?„œ ì±? ? •ë³? ëª©ë¡?„ ? „ë¶? ê°?? ¸?˜¨?‹¤.
	 * 
	 * @return ì±? ? •ë³? ëª©ë¡
	 */
	public List<Map<String, String>> getBookList() {

		return sqlSession.selectList("BookManagement.selectBooks"); //$NON-NLS-1$
	}

	/**
	 * DB?—?„œ ?Š¹? • ì±…ì„ ? œê±°í•œ?‹¤.
	 * 
	 * @param id
	 *            ì±? ID
	 * @return ê²°ê³¼ Int
	 */
	public int deleteBook(int id) {

		return sqlSession.delete("BookManagement.deleteBook", id); //$NON-NLS-1$
	}

	/**
	 * DB?— ì±…ì„ ?“±ë¡í•œ?‹¤.
	 * 
	 * @param bookInfo
	 *            ?“±ë¡? ? •ë³?
	 * @return ê²°ê³¼ Int
	 * @throws ServerQueryException
	 *             ?„œë²? ì¿¼ë¦¬ ê´?? ¨ ?˜ˆ?™¸?‚¬?•­
	 */
	public int insertBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "ì±? ?“±ë¡ì— ?•„?š”?•œ ?•„?ˆ˜ ? •ë³´ê? ?…? ¥?˜ì§? ?•Š?•˜?Šµ?‹ˆ?‹¤."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.insert("BookManagement.insertBook", map); //$NON-NLS-1$
	}

	/**
	 * ì±? ? •ë³´ë?? ?ˆ˜? •?•œ?‹¤.
	 * 
	 * @param bookInfo
	 *            ?ˆ˜? • ? •ë³?
	 * @return ê²°ê³¼ Int
	 * @throws ServerQueryException
	 *             ?„œë²? ì¿¼ë¦¬ ê´?? ¨ ?˜ˆ?™¸?‚¬?•­
	 */
	public int updateBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "ì±? ?“±ë¡ì— ?•„?š”?•œ ?•„?ˆ˜ ? •ë³´ê? ?…? ¥?˜ì§? ?•Š?•˜?Šµ?‹ˆ?‹¤."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.update("BookManagement.updateBook", map); //$NON-NLS-1$
	}
}
