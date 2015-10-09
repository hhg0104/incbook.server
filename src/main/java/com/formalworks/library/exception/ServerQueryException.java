/**
* FileName : ServerQueryException.java
* Created  : 2015. 9. 23.
* Author   : JUNG UK LEE
* Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
*
* 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
* 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
*/
package com.formalworks.library.exception;

/**
 * @author JUNG UK LEE
 *
 */
public class ServerQueryException extends Exception {
	
	private static final long serialVersionUID = -3065936772105612260L;

	private final int errorCode;

	public ServerQueryException(Exception e, int errorCode) {
		super(e);
		this.errorCode = errorCode;
	}

	public ServerQueryException(Exception e, String message, int errorCode) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public ServerQueryException(int errorCode) {
		this.errorCode = errorCode;
	}

	public ServerQueryException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
