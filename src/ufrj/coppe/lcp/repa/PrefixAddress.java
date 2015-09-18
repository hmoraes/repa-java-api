package ufrj.coppe.lcp.repa;

import java.io.Serializable;

/**
 *  
 * This class represents a address used by REP protocol,
 * called Prefix. The Prefix and a Interest put together 
 * compose the Active Prefix used to determinate a Application
 * in a specific node. The Prefix is the node address build
 * probabilistically by REPD daemon using a normal distribution.  
 *  
 * @since REP API 1.0
 * @author Héberte Fernandes de Moraes
 *
 */
public class PrefixAddress implements Serializable {
	private static final long serialVersionUID = -4752613325191737758L;
	
	private int prefix;

	public PrefixAddress() {
		this.prefix = 0;
	}
	
	public PrefixAddress(int prefix) {
		this.prefix = prefix;
	}
	
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	public int getPrefix() {
		return prefix;
	}
	
	@Override
	public String toString() {
		String strPrefix = RepaSocket.prefixToString(prefix); 
		return (strPrefix == null ? "[" + prefix + "]" : strPrefix);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PrefixAddress) {
			return ((PrefixAddress)obj).prefix == this.prefix;
		}
		return super.equals(obj);
	}
}
