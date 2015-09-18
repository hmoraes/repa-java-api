package ufrj.coppe.lcp.repa;

import java.io.InterruptedIOException;
import java.util.List;

/**
 * This class is the socket used to communicate using the REP protocol. 
 * Through this class, you can register, unregister interests, send,
 * receive messages and same other things.
 * 
 * @since REP API 1.0
 * @author Héberte Fernandes de Moraes
 * @see PrefixAddress
 * @see RepaMessage
 * 
 * @version REP API 1.2
 */
public class RepaSocket  {

	private static RepaSocket repaSocket = null;

	private int sock_recv = -1, sock_send = -1;

	/**
	 * Get a new instance socket with locate library
	 * 
	 * @param locate - library locate
	 * 
	 * @deprecated Use operator "new RepaSocket(locate)" for new object
	 */
	@Deprecated	
	public static RepaSocket getRepaSocket(String locate) {
		if (repaSocket == null) {
			repaSocket = new RepaSocket(locate);
		}

		return repaSocket;
	}
	
	/**
	 * 
	 */
	protected RepaSocket() {
		/* Do nothing */
	}

	/**
	 * Create a new instance socket with locate library
	 * 
	 * @throws SecurityException - if a security manager exists and 
	 * 								its checkLink method doesn't allow loading of the specified 
	 * 								dynamic library
	 * @throws UnsatisfiedLinkError - if the file does not exist.
	 * @throws NullPointerException - if filename is null
	 */
	public RepaSocket(String locate) throws SecurityException, 
	UnsatisfiedLinkError, NullPointerException { 
		System.load(locate);
	}

	/**
	 * Open a socket to talk with REPAD daemon (create socket with REPAD daemon)
	 * <br><br>
	 *  .:*@*:. It's very important use this command before use a repad daemon
	 *
	 * @throws RepaSocketException - if an error occurs when opening the socket(include number exception)
	 */
	public native void repaOpen() throws RepaSocketException, ClassNotFoundException;

	/**
	 * Close communication with REPAD service and clean
	 * unregister all interest used by application and
	 * close socket
	 * <br><br>
	 *  .:*@*:. It's very important use this command before
	 *  finish application
	 */
	public native void repaClose() throws RepaSocketException;

	
	/**
	 * Indicate if the communication channel with REPAD service 
	 * is opened or not.
	 * 
	 * @return @true if the channel is closed; @false otherwise
	 *  
	 */
	public boolean isClosed() {
		return (sock_recv <= 0 || sock_send <= 0);
	}

	/**
	 * Convert prefix address in prefix address string format
	 * <br>Its a JNI function
	 *
	 * @param prefix - prefix address in int type
	 * 
	 * @return prefix address string format
	 */
	public native static String prefixToString(int prefix);

	/**
	 * Return prefix address this node
	 *
	 * @return	node address
	 * 
	 * @throws ClassNotFoundException - class not found in JNI
	 */	
	public native PrefixAddress getRepaNodeAddress() throws ClassNotFoundException;

	/**
	 * Receive a message of interest
	 *
	 * @return Message
	 * 
	 * @throws RepaSocketException - socket not open or a error occurs
	 * @throws ClassNotFoundException - class not found in JNI
	 */
	public native RepaMessage repaRecv() throws RepaSocketException, 
	ClassNotFoundException;

	/**
	 * Wait abstime microseconds to receive a message of interest
	 * <br>If timeout return ERROR_TIMEOUT_RECEIVE!
	 *
	 * @param microseconds : waiting for X microseconds a message
	 *
	 * @return Message
	 * 
	 * @throws RepaSocketException - socket not open or a error occurs
	 * @throws ClassNotFoundException - class not found in JNI
	 * @throws InterruptedIOException - Timeout
	 */		
	public native RepaMessage repaTimedRecv(int microseconds) throws 
	RepaSocketException, ClassNotFoundException, InterruptedIOException;

	/**
	 * Send a message of interest
	 *
	 * @param message - container message(data, interest, etc)
	 *
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */		
	public native void repaSend(RepaMessage message) throws 
	ClassNotFoundException, RepaSocketException, RepaConnectException;

	/**
	 * Send a message of interest
	 *
	 * @param message - container message(data, interest, etc)
	 *
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */		
	public native void repaSendHidden(RepaMessage message) throws 
	ClassNotFoundException, RepaSocketException, RepaConnectException;

	/**
	 * Register a interest of application
	 *
	 * @param interest - interest to register
	 *
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */
	public native void registerInterest(String interest) throws 
	RepaConnectException, ClassNotFoundException, RepaSocketException;	

	/**
	 * Unregister a interest of application
	 *
	 * @param interest - interest to unregister
	 *
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */		
	public native void unregisterInterest(String interest) throws 
	RepaConnectException, ClassNotFoundException, RepaSocketException;

	/**
	 * Unregister all interest of application
	 * 
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */
	public native void unregisterAll() throws 
	RepaConnectException, ClassNotFoundException, RepaSocketException;

	/**
	 * Get a list of interest collected by daemon
	 * return the interests in list passed by parameter
	 *
	 * @return list of interest
	 * 
	 * @throws RepaSocketException - socket not open
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */	
	public native List<String> getListInterestsRegistered() throws
	RepaConnectException, ClassNotFoundException, RepaSocketException;
	
	/**
	 * Get a list of interest collected by daemon in NETWORK
	 * return the interests in list passed by parameter
	 *
	 * @return	list of interest
	 * 
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */	
	public native List<String> getListInterestsInNetwork() throws 
	RepaConnectException, ClassNotFoundException;

	/**
	 * Get a list of interest collected by daemon in NETWORK
	 * return the interests in list passed by parameter
	 *
	 * @return	list of interest
	 *  
	 * @deprecated Old functions, new is getListInterestsInNetwork
	 * 
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception) 
	 * @throws ClassNotFoundException - class not found in JNI
	 */
	@Deprecated	
	public List<String> getListInterests() throws 
	RepaConnectException, ClassNotFoundException {
		return getListInterestsInNetwork();
	}

	/**
	 * Get a list of nodes collected by daemon
	 * return the nodes' prefix in list passed by parameter
	 *
	 * @return	list of nodes
	 * 
	 * @throws RepaConnectException - if an error occurs when connect the socket(include number exception)
	 * @throws ClassNotFoundException - class not found in JNI
	 */	
	public native List<PrefixAddress> getListNodes() throws 
	RepaConnectException, ClassNotFoundException;


	public static void main(String[] args) {
		System.out.println("LibRepa calls....");
	}
}
