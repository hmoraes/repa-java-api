package ufrj.coppe.lcp.repa;

public class RepaConnectException extends RepaSocketException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2780163605907850863L;

	public RepaConnectException()
	{
		super();
	}
	
	public RepaConnectException(String msg)
	{
		super(msg);
	}
	
	public RepaConnectException(int numberException, String msg)
	{
		super(numberException,msg);
	}	
	
	public RepaConnectException(int numberException)
	{
		super(numberException);
	}	
	
}
