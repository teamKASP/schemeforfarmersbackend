package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="temp_bidding")
public class Bidding {
	
	@Id
	@SequenceGenerator(name="tempbiddingseq",initialValue=101,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempbiddingseq")
	private int biddingId;
	private double biddingAmount;
	private LocalDate biddingDate;
	private String biddingStatus;
	//private boolean isOpenForBid;
	
	/*@OneToMany(mappedBy="bidding",cascade=CascadeType.ALL)
	private List<Bidder> bidders;
	*/
	
	@ManyToOne
	@JoinColumn(name="bidder_id")
	private Bidder bidder;
	

	@ManyToOne
	@JoinColumn(name="sellRequest_id")
	private PlaceSellrequest sellRequest;
	
	@OneToOne(mappedBy="bidding",cascade=CascadeType.ALL)
	Winner winner;

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	
	public int getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(int biddingId) {
		this.biddingId = biddingId;
	}

	public double getBiddingAmount() {
		return biddingAmount;
	}

	public void setBiddingAmount(double biddingAmount) {
		this.biddingAmount = biddingAmount;
	}

	public LocalDate getBiddingDate() {
		return biddingDate;
	}

	public void setBiddingDate(LocalDate biddingDate) {
		this.biddingDate = biddingDate;
	}

	public String isBiddingStatus() {
		return biddingStatus;
	}

	public void setBiddingStatus(String biddingStatus) {
		this.biddingStatus = biddingStatus;
	}

	/*public List<Bidder> getBidders() {
		return bidders;
	}

	public void setBidders(List<Bidder> bidders) {
		this.bidders = bidders;
	}*/

	public PlaceSellrequest getSellRequest() {
		return sellRequest;
	}

	public void setSellRequest(PlaceSellrequest sellRequest) {
		this.sellRequest = sellRequest;
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}

	public String getBiddingStatus() {
		return biddingStatus;
	}

	@Override
	public String toString() {
		return "Bidding [biddingId=" + biddingId + ", biddingAmount=" + biddingAmount + ", biddingDate=" + biddingDate
				+ ", biddingStatus=" + biddingStatus + ", bidder=" + bidder + ", sellRequest=" + sellRequest
				+ ", winner=" + winner + "]";
	}
	
	
	
	
}
