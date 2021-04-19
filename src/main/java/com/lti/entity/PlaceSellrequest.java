package com.lti.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="temp_sellRequest")
public class PlaceSellrequest {
	
	@Id
	@SequenceGenerator(name="tempsellRequestseq",initialValue=1001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempsellRequestseq")
	private int sellRequestId;
	private double sellQuantity;
	private String fertilizerType;
	private double basePrice;
	private boolean isApproved;
	private String isSold;
	
	
	@OneToMany(mappedBy="sellRequest")
	private List<Bidding> biddings;
	
	@ManyToOne
	@JoinColumn(name="farmer_id")
	Farmer farmer;
	
	@ManyToOne
	@JoinColumn(name="crop_id")
	Crop crop;

	public int getSellRequestId() {
		return sellRequestId;
	}

	public void setSellRequestId(int sellRequestId) {
		this.sellRequestId = sellRequestId;
	}

	public double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}

	
	
	public String getIsSold() {
		return isSold;
	}

	public void setIsSold(String isSold) {
		this.isSold = isSold;
	}

	public List<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(List<Bidding> biddings) {
		this.biddings = biddings;
	}

	@Override
	public String toString() {
		return "PlaceSellrequest [sellRequestId=" + sellRequestId + ", sellQuantity=" + sellQuantity
				+ ", fertilizerType=" + fertilizerType + ", basePrice=" + basePrice + ", isApproved=" + isApproved
				+ ", farmer=" + farmer.getFarmerId() + ", crop=" + crop.getCropId() + "]";
	}
	
	
	
}
