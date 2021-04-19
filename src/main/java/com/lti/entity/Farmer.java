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
@Table(name="temp_farmer")
public class Farmer {
	
	@Id
	@SequenceGenerator(name="tempfarmerseq",initialValue=3001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempfarmerseq")
	private int farmerId;
	private String farmerName;
	private String farmerAddress;
	private String farmerCity;
	private String farmerState;
	private String farmerEmailId;
	private String farmerPassword;
	private double farmerLandArea;
	private String farmerPinCode;
	private String farmerAccountNo;
	private String farmerAdhaarId;
	private String farmerPanId;
	private boolean farmerApprovalStatus;
	private String farmerContactNo;
	
	
	public String getFarmerContactNo() {
		return farmerContactNo;
	}

	public void setFarmerContactNo(String farmerContactNo) {
		this.farmerContactNo = farmerContactNo;
	}



	@OneToMany(mappedBy="farmer",cascade=CascadeType.ALL)
	List<PlaceSellrequest> placeSellRequests;
	
	@OneToOne(mappedBy="farmer",cascade=CascadeType.ALL)
	Wallet wallet;
	
	/*
	@ManyToOne
	@JoinColumn(name="crop_id")
	Crop crop;*/

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getFarmerAddress() {
		return farmerAddress;
	}

	public void setFarmerAddress(String farmerAddress) {
		this.farmerAddress = farmerAddress;
	}

	public String getFarmerCity() {
		return farmerCity;
	}

	public void setFarmerCity(String farmerCity) {
		this.farmerCity = farmerCity;
	}

	public String getFarmerState() {
		return farmerState;
	}

	public void setFarmerState(String farmerState) {
		this.farmerState = farmerState;
	}

	public String getFarmerEmailId() {
		return farmerEmailId;
	}

	public void setFarmerEmailId(String farmerEmailId) {
		this.farmerEmailId = farmerEmailId;
	}

	public String getFarmerPassword() {
		return farmerPassword;
	}

	public void setFarmerPassword(String farmerPassword) {
		this.farmerPassword = farmerPassword;
	}

	public double getFarmerLandArea() {
		return farmerLandArea;
	}

	public void setFarmerLandArea(double farmerLandArea) {
		this.farmerLandArea = farmerLandArea;
	}

	public String getFarmerPinCode() {
		return farmerPinCode;
	}

	public void setFarmerPinCode(String farmerPinCode) {
		this.farmerPinCode = farmerPinCode;
	}

	public String getFarmerAccountNo() {
		return farmerAccountNo;
	}

	public void setFarmerAccountNo(String farmerAccountNo) {
		this.farmerAccountNo = farmerAccountNo;
	}

	public String getFarmerAdhaarId() {
		return farmerAdhaarId;
	}

	public void setFarmerAdhaarId(String farmerAdhaarId) {
		this.farmerAdhaarId = farmerAdhaarId;
	}

	public String getFarmerPanId() {
		return farmerPanId;
	}

	public void setFarmerPanId(String farmerPanId) {
		this.farmerPanId = farmerPanId;
	}

	public boolean isFarmerApprovalStatus() {
		return farmerApprovalStatus;
	}

	public void setFarmerApprovalStatus(boolean farmerApprovalStatus) {
		this.farmerApprovalStatus = farmerApprovalStatus;
	}

	@JsonIgnore
	public List<PlaceSellrequest> getPlaceSellRequests() {
		return placeSellRequests;
	}

	public void setPlaceSellRequests(List<PlaceSellrequest> placeSellRequests) {
		this.placeSellRequests = placeSellRequests;
	}

	/*public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}*/
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
	@Override
	public String toString() {
		return "Farmer [farmerId=" + farmerId + ", farmerName=" + farmerName + ", farmerAddress=" + farmerAddress
				+ ", farmerCity=" + farmerCity + ", farmerState=" + farmerState
				+ ", farmerEmailId=" + farmerEmailId + ", farmerPassword=" + farmerPassword + ", farmerLandArea="
				+ farmerLandArea + ", farmerPinCode=" + farmerPinCode + ", farmerAccountNo=" + farmerAccountNo
				+ ", farmerAdhaarId=" + farmerAdhaarId + ", farmerPanId=" + farmerPanId + ", farmerApprovalStatus="
				+ farmerApprovalStatus /*+ ", crop=" + crop.getCropId() + "]"*/;
	}

	
}
