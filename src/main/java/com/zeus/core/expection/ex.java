package com.zeus.core.expection;

public class ex extends Exception {
	
	private static final long serialVersionUID = 228363131704743788L;
	public ex() {
		super();
	}

	public ex(String msg) {
		super(msg);
	}
	public ex(String msg, Throwable cause) {
		super(msg, cause);
	}
	public ex(Throwable cause) {
		super(cause);
	}
}
