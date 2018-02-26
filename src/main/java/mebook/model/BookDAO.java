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
	 * DB?��?�� �? ?���? 목록?�� ?���? �??��?��?��.
	 * 
	 * @return �? ?���? 목록
	 */
	public List<Map<String, String>> getBookList() {

		return sqlSession.selectList("BookManagement.selectBooks"); //$NON-NLS-1$
	}

	/**
	 * DB?��?�� ?��?�� 책을 ?��거한?��.
	 * 
	 * @param id
	 *            �? ID
	 * @return 결과 Int
	 */
	public int deleteBook(int id) {

		return sqlSession.delete("BookManagement.deleteBook", id); //$NON-NLS-1$
	}

	/**
	 * DB?�� 책을 ?��록한?��.
	 * 
	 * @param bookInfo
	 *            ?���? ?���?
	 * @return 결과 Int
	 * @throws ServerQueryException
	 *             ?���? 쿼리 �??�� ?��?��?��?��
	 */
	public int insertBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "�? ?��록에 ?��?��?�� ?��?�� ?��보�? ?��?��?���? ?��?��?��?��?��."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.insert("BookManagement.insertBook", map); //$NON-NLS-1$
	}

	/**
	 * �? ?��보�?? ?��?��?��?��.
	 * 
	 * @param bookInfo
	 *            ?��?�� ?���?
	 * @return 결과 Int
	 * @throws ServerQueryException
	 *             ?���? 쿼리 �??�� ?��?��?��?��
	 */
	public int updateBook(BookInfo bookInfo) throws ServerQueryException {

		if (bookInfo == null || bookInfo.getTitle() == null) {
			String errMsg = "�? ?��록에 ?��?��?�� ?��?�� ?��보�? ?��?��?���? ?��?��?��?��?��."; //$NON-NLS-1$
			LOGGER.error(errMsg);
			throw new ServerQueryException(errMsg, HttpServletResponse.SC_BAD_REQUEST);
		}

		Map<String, Object> map = bookInfo.toMap();

		return sqlSession.update("BookManagement.updateBook", map); //$NON-NLS-1$
	}
}
