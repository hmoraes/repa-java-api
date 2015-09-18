package ufrj.coppe.lcp.repa.experiment;

import java.net.SocketException;

/**
 * This class is used to make experiments using the 
 * repad daemon. This class allow send pings, timestamps and
 * get the statistics collected. 
 * 
 * @since REP API 1.2
 * @author Héberte Fernandes de Moraes
 * 
 * @version REP API 1.2
 */
public class Experiments {

	public Experiments() throws SecurityException, 
	UnsatisfiedLinkError, NullPointerException { 
		System.loadLibrary("repa");
	}
	
	public Experiments(String locate) throws SecurityException, 
	UnsatisfiedLinkError, NullPointerException { 
		System.load(locate);
	}
	
	/**
	 * Clear all statistics collected by the daemon 
	 * 
	 * @throws {@link Exception}
	 */
	public native int clearStatistics() throws SocketException;

	/**
	 * GetStatistics ask the daemon for all statistics collected
	 * and return in a Statistics class
	 * 
	 * @return {@link Statistics}
	 * @throws {@link Exception}
	 * @throws {@link ClassNotFoundException}
	 */
	public native Statistics getStatistics() throws SocketException, ClassNotFoundException;
	
	/**
	 * SendTimestamp function is a easy way of the synchronize 
	 * all daemons in one hop. This function send the time of
	 * the node, all neighbors calculate their diff times 
	 * with the timed received. 
	 * 
	 * @throws {@link Exception}
	 */
	public native int sendTimestamp() throws SocketException;
	
	/**
	 * SendPing function ask the daemon to send a message only
	 * to neighbors in one hop and each of neighbors will send
	 * a Pong message back.
	 * 
	 * @throws {@link Exception}
	 */
	public native int sendPing() throws SocketException;
}
