package org.eastwill.exception;

import java.sql.SQLException;


public class MySQLException extends SQLException {

	private static final long serialVersionUID = 1L;
	private String sqlCode;
	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}
	public String getSqlCode() {
		return sqlCode;
	}
	public MySQLException() {
		super();
	}
	public MySQLException(String reason, Throwable cause) {
		super(reason, cause);
	}
	public MySQLException(String reason) {
		super(reason);
	}
	public MySQLException(Throwable cause) {
		super(cause);
	}
	
	
}
