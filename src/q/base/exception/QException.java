package q.base.exception;

public class QException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public QException(String message) {
		super(message);
	}
}
