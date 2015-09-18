package ufrj.coppe.lcp.repa;

import java.io.Serializable;

/**
 * This class is used to send/receive interest messages through
 * REP protocol. To send a new interest message is necessary to 
 * create an object of this class with interest, data and the 
 * destination prefix.
 * 
 * @since REP API 1.0
 * @author Héberte Fernandes de Moraes
 * @see PrefixAddress
 * @see RepaSocket
 * 
 * @version REP API 1.1
 */
public class RepaMessage implements Serializable {
	private static final long serialVersionUID = -2726496558866618569L;
	
	private String interest;
	private byte[] data;
	private PrefixAddress srcPrefix;
	private PrefixAddress dstPrefix;
	
	/**
     * Constructs an empty RepaMessage.
     * @since REP API 1.0
     */
	public RepaMessage() {
		this.interest = "";
		this.data = null;
		this.srcPrefix = new PrefixAddress();
		this.dstPrefix = new PrefixAddress();
	}
	
    /**
     * Constructs a RepaMessage setting interest and string data.
     * 
     * @param	interest the message interest for REP protocol tag the message
     * @param	data the string data to send
     * @since REP API 1.0
     */
	public RepaMessage(String interest, String data) {
		this.interest = interest;
		this.data = data.getBytes();
		this.srcPrefix = new PrefixAddress();
		this.dstPrefix = new PrefixAddress();
	}
	
    /**
     * Constructs a RepaMessage setting interest, a byte array with data 
     * and a destination address.
     * 
     * @param	interest the message interest for REP protocol tag the message
     * @param	data the bytes array data to send
     * @param	dstPrefix the destination node (if this is a group message just 
     * 			put pass a new empty PrefixAddress using {@code new PrefixAddress()}) 
     * @since REP API 1.0
     */
	public RepaMessage(String interest, byte[] data, PrefixAddress dstPrefix) {
		this.interest = interest;
		this.data = data;
		this.srcPrefix = new PrefixAddress();
		this.dstPrefix = dstPrefix;
	}
	
    /**
     * Constructs a RepaMessage setting interest, a byte array with data 
     * and a destination address.
     * 
     * @param	interest the message interest for REP protocol tag the message
     * @param	data the string data to send
     * @param	dstPrefix the destination node (if this is a group message just 
     * 			put pass a new empty PrefixAddress using {@code new PrefixAddress()}) 
     * @since REP API 1.0
     */
	public RepaMessage(String interest, String data, PrefixAddress dstPrefix) {
		this.interest = interest;
		this.data = data.getBytes();
		this.srcPrefix = new PrefixAddress();
		this.dstPrefix = dstPrefix;
	}
	
    /**
     * Returns the interest of message.
     *
     * @return the interest of message
     * @since REP API 1.0
     */
	public String getInterest() {
		return interest;
	}

    /**
     * Sets the interest to this message
     *
     * @param interest the interest to tag this message
     * @since REP API 1.0
     */
	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	/**
     * Used to get data of message.
     *
     * @return the data of message
     * @since REP API 1.0
     */
	public byte[] getData() {
		return data;
	}
	
    /**
     * Sets the bytes array with data to this message
     *
     * @param data the bytes array to tag this message
     * @since REP API 1.0
     */
	public void setData(byte[] data) {
		this.data = data;
	}
	
	/**
     * Used to get the Prefix of message.
     *
     * @return the PrefixAddress
     * @deprecated use some other function like getSrcPrefix() or getDstPrefix()
     * @since REP API 1.0
     */
	public PrefixAddress getPrefix() {
		return srcPrefix;
	}
	
    /**
     * Sets the prefix address to this message
     *
     * @param prefix the prefix address to set
     * @deprecated use some other function like setSrcPrefix() or setDstPrefix()
     * @since REP API 1.0
     */
	public void setPrefix(PrefixAddress prefix) {
		this.dstPrefix = prefix;
	}
	
    /**
     * toString method used to print the RepaMessage object in the following format
     * [<DstPrefix><SrcPrefix><Interest><data>]
     *
     * @return the RepaMessage in a readable format (useful in debug mode)
     * @since REP API 1.0
     */
	@Override
	public String toString() {
		return "[<" + dstPrefix + "><" + srcPrefix + "><" + interest + "><" + new String(data) + ">]";
	}
	
	/**
     * Used to get the Source prefix of message.
     *
     * @return the source Prefix Address
     * @since REP API 1.1
     */
	public PrefixAddress getSrcPrefix() {
		return srcPrefix;
	}
	
	/**
     * Used to get the Destination prefix of message.
     *
     * @return the destination Prefix Address
     * @since REP API 1.1
     */
    public PrefixAddress getDstPrefix() {
		return dstPrefix;
	}

    /**
     * Sets the destination prefix address to this message
     *
     * @param prefix the prefix address to set
     * @since REP API 1.1
     */
	public void setDstPrefix(PrefixAddress prefix) {
		this.dstPrefix = prefix;
	}
	
	/**
	 * Indicate if the message is for a group of nodes
	 * 
	 * @return @true if the message is for interest group, @false otherwise
	 * @since REP API 1.1
	 */
	public boolean isInterestGroup() {
		return ((dstPrefix == null) || (dstPrefix.getPrefix() == 0));
	}
	
	/**
	 * Indicate if the message is for a specific node
	 * 
	 * @return @true if the message is a specific node, @false otherwise
	 * @since REP API 1.1
	 */
	public boolean isSourceToDestination() {
		return !isInterestGroup();
	}
}
