package incbook.exception;

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
