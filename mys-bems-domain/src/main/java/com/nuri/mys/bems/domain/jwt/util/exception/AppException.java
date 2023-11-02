package com.nuri.mys.bems.domain.jwt.util.exception;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6598321209718394657L;

	private int Err_Code;

	public AppException(String msg, int errCode) {
		super(msg);
		this.Err_Code = errCode;
	}

	public AppException(String msg) {
		this(msg, 500);
	}

	public int getErrCode() {
		return Err_Code;
	}
}
