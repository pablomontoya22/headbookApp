package app.helpers;

import java.util.Date;

public class Response {

	private final int code;
	private final Object data;
	private final String status;
	private final String message;
	private final long timestamp;

	public Response(boolean data, String message) {
		code = data ? HTTPCodes.OK : HTTPCodes.INTERNAL_SERVER_ERROR;
		this.data = data;
		this.message = message;
		status =  this.code > 300 ? "Error" : "OK";
		timestamp = new Date().getTime();
	}

	public Response(Object data) {
		code = HTTPCodes.OK;
		this.data = data;
		message = "";
		status =  this.code > 300 ? "Error" : "OK";
		timestamp = new Date().getTime();
	}

	public int getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
