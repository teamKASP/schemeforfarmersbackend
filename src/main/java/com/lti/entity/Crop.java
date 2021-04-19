package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="temp_crop")
public class Crop {
	
	@Id
	@SequenceGenerator(name="tempcropseq",initialValue=5001,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tempcropseq")
	private int cropId;
	private String cropName;
	private String cropType;
	
	/*@OneToMany(mappedBy="crop",cascade=CascadeType.ALL)
	List<Farmer> farmers;*/

	@OneToMany(mappedBy="crop",cascade=CascadeType.ALL)
	List<PlaceSellrequest> sellRequests;
	
	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	/*public List<Farmer> getFarmers() {
		return farmers;
	}

	public void setFarmers(List<Farmer> farmers) {
		this.farmers = farmers;
	}*/

	public List<PlaceSellrequest> getSellRequests() {
		return sellRequests;
	}

	public void setSellRequests(List<PlaceSellrequest> sellRequests) {
		this.sellRequests = sellRequests;
	}
	
	@Override
	public String toString() {
		return "Crop [cropId=" + cropId + ", cropName=" + cropName + ", cropType=" + cropType+ "]";
	}
	
	
}
