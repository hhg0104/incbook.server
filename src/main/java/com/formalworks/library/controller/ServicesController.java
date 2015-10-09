/**
 * FileName : ServicesController.java
 * Created  : 2015. 6. 25.
 * Author   : Jung Uk Lee
 * Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
 *
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */

package com.formalworks.library.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicesController {

	static final int AUTHORITATIVE_INFORMATION = HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION;

	static final int BAD_REQUEST = HttpServletResponse.SC_BAD_REQUEST;

	static final int INTERNAL_SERVER_ERROR = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

	static final int CONFLICT = HttpServletResponse.SC_CONFLICT;

	static final int OK = HttpServletResponse.SC_OK;

	static final String AUTHORIZED_TOKEN = "authorizedToken"; //$NON-NLS-1$

	static final String DEFAULT_MESSAGE = "DM"; //$NON-NLS-1$

	static final String DESCRIPTION = "description"; //$NON-NLS-1$

	static final String EMPTY_STRING = ""; //$NON-NLS-1$

	static final String IS_ADMIN = "isAdmin"; //$NON-NLS-1$

	static final String MESSAGE = "message"; //$NON-NLS-1$

	static final String NEWPASSWORD = "newPassword"; //$NON-NLS-1$

	static final String OLDPASSWORD = "oldPassword"; //$NON-NLS-1$

	static final String PASSWORD = "password"; //$NON-NLS-1$

	static final String USER_ID = "userID"; //$NON-NLS-1$

	static final String USER_NAME = "userName"; //$NON-NLS-1$
	
	static final String ISSUEID = "issueID"; //$NON-NLS-1$

	static final String GROUPID = "groupID"; //$NON-NLS-1$

	static final String TYPEID = "typeID"; //$NON-NLS-1$

	static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

	static final String FIELDS = "fields"; //$NON-NLS-1$

	static final String FORMALWORKS_BOOK_ID = "formalworksBookID"; //$NON-NLS-1$

	static final String BOOK_ID = "book_id"; //$NON-NLS-1$

	static final String TEXT_NOT_LOGINED = "로그인이 되어있지 않습니다."; //$NON-NLS-1$

	private static final Logger LOGGER = Logger.getLogger(ServicesController.class);

	protected Properties userInfo = new Properties();

	@Autowired
	public HttpServletRequest request;

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
