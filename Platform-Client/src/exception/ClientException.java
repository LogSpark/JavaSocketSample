package exception;

public class ClientException extends RuntimeException {
	/**
	 * ���������쳣
	 */
	private static final long serialVersionUID = 1L;

	public ClientException(String s)
	{
		super(s);
	}
}
