package exception;

public class ClientException extends RuntimeException {
	/**
	 * 处理连接异常
	 */
	private static final long serialVersionUID = 1L;

	public ClientException(String s)
	{
		super(s);
	}
}
