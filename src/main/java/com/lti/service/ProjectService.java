package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Bidding;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.PlaceSellrequest;
import com.lti.entity.Winner;

public interface ProjectService {
	
	public void getFarmerSellHistory(int farmerId);
	
	public Farmer addOrUpdateFarmer(Farmer farmer);

	public Bidder addOrUpdateBidder(Bidder bidder);

	public void placeBid(int bidderId, int sellRequestId, int amount);

	public void startBidding(int sellRequestId);

	public Bidding stopBidding(int sellRequestId);
	
	public PlaceSellrequest farmerRequestCropForSale(int farmerId, int cropId);

	public Farmer farmerLogin(String farmerEmail, String farmerPassword);

	public Bidder bidderLogin(String bidderEmail, String bidderPassword);
	
	public List<Farmer> newFarmers();
	
	public Admin addAdmin(Admin admin);
	


}
