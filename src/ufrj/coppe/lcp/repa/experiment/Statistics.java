package ufrj.coppe.lcp.repa.experiment;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Statistics {
	
	private int qtyMessageUniqueReceive; // Unique messages received after matching filter
	private int qtyMessageTotalReceived; // Messages received before matching filter
	private int qtyMessageAcceptInterest; // Unique messages accepted by interest

	private int qtyMessageDroppedPrefixP; // Messages dropped by unmatched prefix P
	private int qtyMessageDroppedMaxHTL; // Messages dropped by achieve maximum htl
	private int qtyMessageDroppedMemory; // Messages dropped by memory strategy (to filter unique messages)

	private int qtyMessageSent; // Messages forward (node not match interest)
	private int qtyMessageSentCollaborative; // Messages forward Collaboratively

	private int meanHtl; // Mean Hop to live for accepted interest messages
	
	private int meanHtlForAll; // Mean Hop to live for all messages
	private long meanLatency; // Mean latency for accepted interest messages
	private long meanPropagationTime; // Mean Propagation time

	
	public Statistics(byte[] data) {
		ByteBuffer dataByteBuffer = ByteBuffer.wrap(data);
		dataByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		
		qtyMessageUniqueReceive = dataByteBuffer.getInt(); // Unique messages received after matching filter
		qtyMessageTotalReceived = dataByteBuffer.getInt(); // Messages received before matching filter
		qtyMessageAcceptInterest = dataByteBuffer.getInt(); // Unique messages accepted by interest

		qtyMessageDroppedPrefixP = dataByteBuffer.getInt(); // Messages dropped by unmatched prefix P
		qtyMessageDroppedMaxHTL = dataByteBuffer.getInt(); // Messages dropped by achieve maximum htl
		qtyMessageDroppedMemory = dataByteBuffer.getInt(); // Messages dropped by memory strategy (to filter unique messages)

		qtyMessageSent = dataByteBuffer.getInt(); // Messages forward (node not match interest)
		qtyMessageSentCollaborative = dataByteBuffer.getInt(); // Messages forward Collaboratively

		meanHtl = dataByteBuffer.getInt(); // Mean Hop to live for accepted interest messages
		meanLatency = dataByteBuffer.getLong(); // Mean latency for accepted interest messages
		meanPropagationTime = dataByteBuffer.getLong(); // Mean Propagation time

		meanHtlForAll = dataByteBuffer.getInt(); // Mean Hop to live for all messages
	}
	
	public Statistics(int qtyMessageUniqueReceive, int qtyMessageTotalReceived,
			int qtyMessageAcceptInterest, int qtyMessageDroppedPrefixP,
			int qtyMessageDroppedMaxHTL, int qtyMessageDroppedMemory,
			int qtyMessageSent, int qtyMessageSentCollaborative, int meanHtl,
			int meanHtlForAll, long meanLatency, long meanPropagationTime) {
		super();
		this.qtyMessageUniqueReceive = qtyMessageUniqueReceive;
		this.qtyMessageTotalReceived = qtyMessageTotalReceived;
		this.qtyMessageAcceptInterest = qtyMessageAcceptInterest;
		this.qtyMessageDroppedPrefixP = qtyMessageDroppedPrefixP;
		this.qtyMessageDroppedMaxHTL = qtyMessageDroppedMaxHTL;
		this.qtyMessageDroppedMemory = qtyMessageDroppedMemory;
		this.qtyMessageSent = qtyMessageSent;
		this.qtyMessageSentCollaborative = qtyMessageSentCollaborative;
		this.meanHtl = meanHtl;
		this.meanHtlForAll = meanHtlForAll;
		this.meanLatency = meanLatency;
		this.meanPropagationTime = meanPropagationTime;
	}

	public int getQtyMessageUniqueReceive() {
		return qtyMessageUniqueReceive;
	}

	public int getQtyMessageTotalReceived() {
		return qtyMessageTotalReceived;
	}

	public int getQtyMessageAcceptInterest() {
		return qtyMessageAcceptInterest;
	}

	public int getQtyMessageDroppedPrefixP() {
		return qtyMessageDroppedPrefixP;
	}

	public int getQtyMessageDroppedMaxHTL() {
		return qtyMessageDroppedMaxHTL;
	}

	public int getQtyMessageDroppedMemory() {
		return qtyMessageDroppedMemory;
	}

	public int getQtyMessageSent() {
		return qtyMessageSent;
	}

	public int getQtyMessageSentCollaborative() {
		return qtyMessageSentCollaborative;
	}

	public int getMeanHtl() {
		return meanHtl;
	}

	public long getMeanLatency() {
		return meanLatency;
	}

	public long getMeanPropagationTime() {
		return meanPropagationTime;
	}

	public int getMeanHtlForAll() {
		return meanHtlForAll;
	}
	
	public String printStatistics() {
		return qtyMessageUniqueReceive + " " + qtyMessageTotalReceived + 
				" " + qtyMessageAcceptInterest + " " + qtyMessageDroppedPrefixP  + " " + qtyMessageDroppedMaxHTL  + 
				" " + qtyMessageDroppedMemory  + " " + qtyMessageSent  + " " + qtyMessageSentCollaborative + 
				" " + meanHtl + " " + meanLatency + " " + meanPropagationTime + " " + meanHtlForAll;
	}
	
	@Override
	public String toString() {
		return "Statistics: " + printStatistics();
	}
}
