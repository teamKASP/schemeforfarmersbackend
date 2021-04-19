package com.lti.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="temp_winner")
public class Winner {
	@Id
	@SequenceGenerator(name="tempwinnerseq",initialValue=9001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempwinnerseq")
	int winnerId;
	
	@OneToOne
	@JoinColumn(name="bidding_id")
	Bidding bidding;

	public int getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}

	public Bidding getBidding() {
		return bidding;
	}

	public void setBidding(Bidding bidding) {
		this.bidding = bidding;
	}

	@Override
	public String toString() {
		return "Winner [winnerId=" + winnerId + ", bidding=" + bidding + "]";
	}
	
	
	
	
}
