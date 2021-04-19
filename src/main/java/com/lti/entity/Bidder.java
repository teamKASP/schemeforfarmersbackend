package com.lti.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="temp_bidder")
public class Bidder {
	@Id
	@SequenceGenerator(name="tempbidderseq",initialValue=7001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempbidderseq")
	private int bidderId;
	private String bidderName;
	private String bidderContact;
	private String bidderEmail;
	private String bidderPassword;
	private String bidderAddress;
	private String bidderCity;
	private String bidderState;
	private String bidderPincode;
	private String bidderAccountNo;
	private String bidderAdhaarNo;
	private String bidderPanNo;
	//private String bidderTraderLicenseNo;
	//private boolean bidderApprovalStatus;	
	@OneToMany(mappedBy="bidder", cascade=CascadeType.ALL)
	private List<Bidding> biddings;
	
	/*
	@ManyToOne
	@JoinColumn(name="bidding_id")
	private Bidding bidding;
	*/
	@OneToOne(mappedBy="bidder",cascade=CascadeType.ALL)
	private Wallet wallet;

	public int getBidderId() {
		return bidderId;
	}

	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public String getBidderContact() {
		return bidderContact;
	}

	public void setBidderContact(String bidderContact) {
		this.bidderContact = bidderContact;
	}

	public String getBidderEmail() {
		return bidderEmail;
	}

	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}

	public String getBidderPassword() {
		return bidderPassword;
	}

	public void setBidderPassword(String bidderPassword) {
		this.bidderPassword = bidderPassword;
	}

	public String getBidderAddress() {
		return bidderAddress;
	}

	public void setBidderAddress(String bidderAddress) {
		this.bidderAddress = bidderAddress;
	}

	public String getBidderCity() {
		return bidderCity;
	}

	public void setBidderCity(String bidderCity) {
		this.bidderCity = bidderCity;
	}

	public String getBidderState() {
		return bidderState;
	}

	public void setBidderState(String bidderState) {
		this.bidderState = bidderState;
	}

	public String getBidderPincode() {
		return bidderPincode;
	}

	public void setBidderPincode(String bidderPincode) {
		this.bidderPincode = bidderPincode;
	}

	public String getBidderAccountNo() {
		return bidderAccountNo;
	}

	public void setBidderAccountNo(String bidderAccountNo) {
		this.bidderAccountNo = bidderAccountNo;
	}

	public String getBidderAdhaarNo() {
		return bidderAdhaarNo;
	}

	public void setBidderAdhaarNo(String bidderAdhaarNo) {
		this.bidderAdhaarNo = bidderAdhaarNo;
	}

	public String getBidderPanNo() {
		return bidderPanNo;
	}

	public void setBidderPanNo(String bidderPanNo) {
		this.bidderPanNo = bidderPanNo;
	}

	/*public Bidding getBidding() {
		return bidding;
	}

	public void setBidding(Bidding bidding) {
		this.bidding = bidding;
	}*/

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@JsonIgnore
	public List<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(List<Bidding> biddings) {
		this.biddings = biddings;
	} 
	
	
	
}
