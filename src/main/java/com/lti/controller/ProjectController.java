package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidderLoginDto;
import com.lti.dto.FarmerLoginDto;
import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Bidding;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.PlaceSellrequest;
import com.lti.entity.Winner;
import com.lti.service.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	ProjectService service;
	
	@PostMapping(value="addAdmin")
	public Admin addAdmin(Admin admin) {
		return service.addAdmin(admin);
	}
	
	@RequestMapping(value="/registerfarmer", method = RequestMethod.POST)
	public Farmer addOrUpdateFarmer(@RequestBody Farmer farmer) {
		// TODO Auto-generated method stub
		return service.addOrUpdateFarmer(farmer);
	}
	
	@RequestMapping(value="/registerbidder", method = RequestMethod.POST)
	public Bidder addOrUpdateBidder(@RequestBody Bidder bidder) {
		// TODO Auto-generated method stub
		return service.addOrUpdateBidder(bidder);
	}

	@RequestMapping(value="/farmerlogin", method = RequestMethod.POST)
	public boolean farmerLogin(@RequestBody FarmerLoginDto fLogin) {
		Farmer farmer=service.farmerLogin(fLogin.getFarmerEmail(),fLogin.getFarmerPassword());
		System.out.println(farmer);
		if(farmer!=null) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/bidderlogin", method = RequestMethod.POST)
	public Bidder bidderLogin(@RequestBody BidderLoginDto bLogin) {
		Bidder bidder=service.bidderLogin(bLogin.getBidderEmail(),bLogin.getBidderPassword());
		System.out.println(bidder);
		System.out.println(bLogin.getBidderEmail()+" "+bLogin.getBidderPassword());
		if(bidder!=null) {
			return bidder;
		}
		return null;
	}
	
	@GetMapping(value="/newregisterationsfarmers")
	public List<Farmer> newFarmers(){
		return (List<Farmer>)service.newFarmers();
	}
	
	public PlaceSellrequest farmerRequestCropForSale(int farmerId, int cropId) {
		return service.farmerRequestCropForSale(farmerId, cropId);
	}
	
	public void startBidding(int sellRequestId) {
		// TODO Auto-generated method stub
		service.startBidding(sellRequestId);
		
	}

	public Bidding stopBidding(int sellRequestId) {
		// TODO Auto-generated method stub
		return service.stopBidding(sellRequestId);
	}
	
	public void getFarmerSellHistory(int farmerId) {
		service.getFarmerSellHistory(farmerId);
	}
	
	public void placeBid(int bidderId, int sellRequestId,int amount) {
		service.placeBid(bidderId, sellRequestId, amount);
	}
}
