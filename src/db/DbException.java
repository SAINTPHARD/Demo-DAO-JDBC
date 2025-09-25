package db;

public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}
	
	/**
	// Adicionado construtor que receber msg  e/para encapsular exceções originais de erro
	 * @param msg A mensagem de erro
	 * @param cause A causa original da exceção ex: SQLException
	 */
	public DbException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
