package com.lti.repository;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Bidding;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.PlaceSellrequest;
import com.lti.entity.Winner;

public interface ProjectDao {
	
	public Crop getCropByName();

	public Farmer addOrUpdateFarmer(Farmer farmer);

	public Farmer findFarmerById(int farmerId);

	public List<Farmer> viewAllFarmers();

	public Crop findCropById(int cropId);

	public Crop addOrUpdateCrop(Crop crop);

	public PlaceSellrequest farmerRequestCropForSale(PlaceSellrequest psrq);

	public Bidder addOrUpdateBidder(Bidder bidder);

	public PlaceSellrequest findSellRequestById(int sellRequestId);

	public Bidder findBidderById(int bidderId);

	public void placeBid(Bidding bidding, int bidderId, int sellrequestId);

	public void startBidding(int sellRequestId);

	public Bidding stopBidding(int sellRequestId);

	public void addWinner(Winner winner);

	public String checkCropStatus(int sellrequestId);

	public String verifyBid(int bidderId, int sellRequestId);
	
	public double checkBidAmount(int sellRequestId);
	
	public List<Winner> getWinnerByBidderId(int bidderId);
	
	public Bidder getBidderByFarmerId(int farmerId);
	
	public Bidder getWinnerBySellRequestId(int sellRequestId);
	
	public Bidding getWinnerByBidding(int sellRequestId);
	
	public List<PlaceSellrequest> findSellRequestByFarmerId(int farmerId);

	public Farmer farmerLogin(String email, String password);

	public Bidder bidderLogin(String bidderEmail, String bidderPassword);
	
	public List<Farmer> newFarmers();
	
	public Admin addAdmin(Admin admin);
}
