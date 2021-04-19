package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.repository.ProjectDao;
import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Bidding;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.PlaceSellrequest;
import com.lti.entity.Winner;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDao dao;
	
	public Admin addAdmin(Admin admin) {
		return  dao.addAdmin(admin);
	}
	
	public Farmer addOrUpdateFarmer(Farmer farmer) {
		// no change
		farmer.setFarmerApprovalStatus(false);
		return dao.addOrUpdateFarmer(farmer);
	}
	
	public PlaceSellrequest farmerRequestCropForSale(int farmerId,int cropId ) {
		Farmer farmer=dao.findFarmerById(3003);
		Crop crop=dao.findCropById(5003);
		
		PlaceSellrequest psrq=new PlaceSellrequest();
		psrq.setCrop(crop);
		psrq.setFertilizerType("Type A");
		psrq.setSellQuantity(1);
		psrq.setApproved(false);
		psrq.setBasePrice(2000);
		//psrq.setSold(false);
		psrq.setFarmer(farmer);
		//psrq.setSellRequestId(1021);
		
		PlaceSellrequest psrqPersisted= dao.farmerRequestCropForSale(psrq);
		
		return psrqPersisted;
	}

	public Farmer farmerLogin(String email, String password) {
		return dao.farmerLogin(email,password);
	}
	
	public Bidder addOrUpdateBidder(Bidder bidder) {
		// no change
		return dao.addOrUpdateBidder(bidder);	
	}
	
	public Bidder bidderLogin(String bidderEmail, String bidderPassword) {
		return dao.bidderLogin(bidderEmail,bidderPassword);
	}
	
	public List<Farmer> newFarmers(){
		return (List<Farmer>)dao.newFarmers();
	}

	public void placeBid(int bidderId, int sellRequestId,int amount) {
		Bidder bidder=dao.findBidderById(bidderId);
		PlaceSellrequest sellRequest=dao.findSellRequestById(sellRequestId);
		
		/*
		int bidderId=bidder.getBidderId();
		int sellRequestId=sellRequest.getSellRequestId();
		*/
		Bidding bidding = new Bidding();
		bidding.setBiddingAmount(amount);
		bidding.setBiddingDate(LocalDate.now());
		bidding.setBiddingStatus("Active");
		bidding.setSellRequest(sellRequest);
		bidding.setBidder(bidder);
		
		double currentBid=bidding.getBiddingAmount();
		double previousBid=dao.checkBidAmount(sellRequestId);

		String status = dao.checkCropStatus(sellRequestId);
		String verify = dao.verifyBid(bidderId, sellRequestId);
		if((status!=null) && (status.equalsIgnoreCase("Unsold"))) {
			if((verify!=null) && (verify.equalsIgnoreCase("Active"))) {
				System.out.println("Your bid is the current one");
			}
			else {
				if(verify!=null) {
					if(currentBid>previousBid) {
						dao.placeBid(bidding, bidderId, sellRequestId);
					}
					else {
						System.out.println("Current Bidding must be more than the previous one.");
					}
				}
				else {
					if(currentBid>previousBid) {
						dao.placeBid(bidding, bidderId, sellRequestId);
					}
					else {
						System.out.println("Current Bidding must be more than the previous one.");
					}
				}
				
			}
		}
		else if((status!=null) && (status.equalsIgnoreCase("Sold"))){
			System.out.println("Crop Sold! Bidding Not Allowed");
		}
		else {
			System.out.println("Bidding Not Yet Started");
		}
		
	}

	public void startBidding(int sellRequestId) {
		// TODO Auto-generated method stub
		dao.startBidding(sellRequestId);
		
	}

	//change return type from Bidding to winnerDTO
	/**
	 *
	 */
	public Bidding stopBidding(int sellRequestId) {
		// change
		Bidding bidding=dao.stopBidding(sellRequestId);
		Winner winner=new Winner();
        winner.setBidding(bidding);
        dao.addWinner(winner);	//removed
        bidding=dao.getWinnerByBidding(sellRequestId);	//removed
        Bidder bidder=bidding.getBidder();
        double amt=bidding.getBiddingAmount();
        PlaceSellrequest sellrequest=bidding.getSellRequest();
        Crop crop=sellrequest.getCrop();
        Farmer farmer=sellrequest.getFarmer();
        System.out.println(bidder.getBidderId()+" "+bidder.getBidderName()+" "+amt+" "+crop.getCropName()+" "+farmer.getFarmerName());
		return bidding;
		
	}

	public void getFarmerSellHistory(int farmerId) {
		Farmer farmer=dao.findFarmerById(farmerId);
		//System.out.println(farmer);
		List<PlaceSellrequest> sellrequests=dao.findSellRequestByFarmerId(farmerId);
		//List<Bidding> biddings=new ArrayList<Bidding>();
		for (PlaceSellrequest sellrequest : sellrequests) {
			if(sellrequest.getIsSold().equalsIgnoreCase("sold"))
			{
				int sellId = sellrequest.getSellRequestId();
				//System.out.println(sellId);
				Bidding bidding=dao.getWinnerByBidding(sellId);
		        Bidder bidder=bidding.getBidder();
		        double amt=bidding.getBiddingAmount();
		        PlaceSellrequest sellrqst=bidding.getSellRequest();
		        Crop crop=sellrqst.getCrop();
		        Farmer frmr=sellrqst.getFarmer();
		        System.out.println(bidder.getBidderId()+" "+bidder.getBidderName()+" "+amt+" "+crop.getCropName()+" "+frmr.getFarmerName());
			}
		}
	}

}
