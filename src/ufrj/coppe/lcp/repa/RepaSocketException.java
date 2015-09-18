package ufrj.coppe.lcp.repa;

import java.net.SocketException;

/**
 * This class represents all errors occurred in the native library
 * 
 * @since REP API 1.1
 * @author Michael Douglas
 * @see RepaSocket
 * 
 * @version REP API 1.1
 */
public class RepaSocketException extends SocketException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7269769085516472637L;

	private int numberException;
	
	/* ************************* Errors ************************* */
	/* ********************************************************** */
	/* !!!!!  This errors is equal repa.h, so modify BOTH   !!!!! */
	/*                 !!!!!!! Comments, too !!!!!!!              */ 
	/** !!!  And describe in the getErrorMessage() function !!!  **/
	/* ********************************************************** */
	/* ********************************************************** */	
	public final static int OK									= 0;
	public final static int ERROR_CREATE_SOCKET					= -1;
	public final static int ERROR_CONNECT_SOCKET				= -2;
	public final static int ERROR_SOCKET_NOT_OPEN				= -3;
	public final static int ERROR_SEND_SOCKET					= -4;
	public final static int ERROR_RECVFROM_SOCKET				= -5;
	public final static int ERROR_INVALID_PARAM					= -6; // Param null
	public final static int ERROR_CREATE_HASHMAP				= -10;
	public final static int ERROR_TOO_LONG_MESSAGE				= -11;
	public final static int ERROR_TIMEOUT_RECEIVE				= -12;
	public final static int ERROR_SELECT_DESCRIPTOR				= -13;
	public final static int ERROR_UNKNOWN_SOCKET				= -14;
	// Added in 19/04/2013
	public final static int ERROR_RECV_SOCKET					= -15;
	public final static int ERROR_JNI_FAILURE					= -16;	
	/* ********************************************************** */	
	
	public RepaSocketException()
	{
		super();
		numberException = OK;
	}
	
	public RepaSocketException(String msg)
	{
		super(msg);
		numberException = OK;
	}
	
	public RepaSocketException(int numberException, String msg)
	{
		super(msg);
		this.numberException = numberException;
	}	
	
	public RepaSocketException(int numberException)
	{
		super();
		this.numberException = numberException;
	}	
	
	public int getNumberException()
	{
		return numberException;
	}
	
	protected String getErrorMessage(int numberException) {
		switch (numberException) {
		case ERROR_CREATE_SOCKET:
			return "Error on create a new socket";
		case ERROR_CONNECT_SOCKET:
			return "Error on try to connect the socket";
		case ERROR_SOCKET_NOT_OPEN:
			return "Error on try open the socket";
		case ERROR_SEND_SOCKET:
			return "Error on try send a data";
		case ERROR_RECVFROM_SOCKET:
		case ERROR_RECV_SOCKET:
			return "Error on try receive a data";
		case ERROR_INVALID_PARAM:
			return "Error invalid paramater data passed to the function";
		case ERROR_CREATE_HASHMAP:
			return "Error on try create the hashmap";
		case ERROR_TOO_LONG_MESSAGE:
			return "Error message sent is too long";
		case ERROR_TIMEOUT_RECEIVE:
			return "Error receive time expired";
		case ERROR_SELECT_DESCRIPTOR:
			return "Error on select decriptor";
		case ERROR_UNKNOWN_SOCKET:
			return "Error unknown socket";
		case ERROR_JNI_FAILURE:
			return "Error JNI failure";
		default:
			return "Same other error in JNI or not defined yet";
		}
	}
	
	public String toString() {
		if(getMessage() != null) {
			return this.getClass().getName() + " : " + getMessage() + " - Type: " 
				+ getNumberException() + " - Message: " + getErrorMessage(getNumberException());
		} else {
			return this.getClass().getName() + " - Type: " + getNumberException() 
				+ " - Message: " + getErrorMessage(getNumberException());
		}
	}
}
