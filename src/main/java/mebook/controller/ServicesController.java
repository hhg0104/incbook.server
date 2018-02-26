
package mebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ServicesController {

	private static final Logger LOGGER = Logger.getLogger(ServicesController.class);

	protected static final String DEFAULT_MESSAGE = "DM"; //$NON-NLS-1$

	protected static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

	protected Properties userInfo = new Properties();

	/**
	 * AuthorizedToken을 생성한다.
	 * 
	 * @return
	 */
	protected String generateRandomBigInteger() {
		return new BigInteger(130, new SecureRandom()).toString(32);
	}

	/**
	 * Response를 전송한다.
	 * 
	 * @param response
	 * @param errorCode
	 */
	protected void sendResponse(HttpServletResponse response, int errorCode) {
		sendResponse(response, errorCode, DEFAULT_MESSAGE);
	}

	/**
	 * Response를 전송한다.
	 * 
	 * @param response
	 * @param errorCode
	 * @param message
	 */
	protected void sendResponse(HttpServletResponse response, int errorCode, String message) {

		try {
			response.setStatus(errorCode);
			response.setCharacterEncoding(UTF_8);
			PrintWriter writer = response.getWriter();
			writer.append(message);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
