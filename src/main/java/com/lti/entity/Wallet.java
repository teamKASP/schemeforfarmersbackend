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
@Table(name="temp_wallet")
public class Wallet {
	@Id
	@SequenceGenerator(name="tempwalletseq",initialValue=9001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempwalletseq")
	int walletId;
	
	@OneToOne
	@JoinColumn(name="farmer_id")
	Farmer farmer;
	
	@OneToOne
	@JoinColumn(name="bidder_id")
	Bidder bidder;
	
	private double walletAmount;

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	
}
