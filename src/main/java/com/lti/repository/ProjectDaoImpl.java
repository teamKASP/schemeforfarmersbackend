package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Bidding;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.PlaceSellrequest;
import com.lti.entity.Winner;

//@Component
//@Qualifier("dao")
@Repository
public class ProjectDaoImpl implements ProjectDao {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Admin addAdmin(Admin admin) {
		return (Admin)em.merge(admin);
	}

	// Add a new Farmer or update Existing Farmer
	@Transactional
	public Farmer addOrUpdateFarmer(Farmer farmer) {
		Farmer farmerpersisted = em.merge(farmer);
		return farmerpersisted;
	}
	
	public Farmer farmerLogin(String email, String password) {
		String jpql="select f from Farmer f where f.farmerEmailId=?1 and f.farmerPassword=?2";
		TypedQuery<Farmer> query= em.createQuery(jpql,Farmer.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		Farmer farmer= null;
		try {
			farmer=query.getSingleResult();
		} catch (Exception e) {
		}
		return farmer;
	}
	
	public List<Farmer> newFarmers(){
		String jpql = "select f from Farmer f where f.farmerApprovalStatus = false";
		List<Farmer> farmers=null;
		try {
			farmers=em.createQuery(jpql, Farmer.class).getResultList();
		} catch (Exception e) {
		}
		return farmers;
		
	}

	// find farmer by farmer ID;
	public Farmer findFarmerById(int farmerId) {
		return (Farmer) em.find(Farmer.class, farmerId);		
	}
	
	// list the details of all farmers
	public List<Farmer> viewAllFarmers() {
		String jpql = "select f from Farmer f order by f.farmerId";
		return (List<Farmer>) em.createQuery(jpql, Farmer.class).getResultList();		
	}

	// find crop by crop Id
	public Crop findCropById(int cropId) {
		return em.find(Crop.class, cropId);
	}

	// Farmer will add crop for sale
	@Transactional
	public Crop addOrUpdateCrop(Crop crop) {
		return (Crop)em.merge(crop);
	}

	// farmer will request crop added for sale
	@Transactional
	public PlaceSellrequest farmerRequestCropForSale(PlaceSellrequest psrq) {
		return em.merge(psrq);
	}

	@Transactional
	public Bidder addOrUpdateBidder(Bidder bidder) {
		return em.merge(bidder);
	}
	
	public Bidder bidderLogin(String bidderEmail, String bidderPassword) {
		String jpql="select b from Bidder b where b.bidderEmail=?1 and b.bidderPassword=?2";
		TypedQuery<Bidder> query= em.createQuery(jpql,Bidder.class);
		query.setParameter(1, bidderEmail);
		query.setParameter(2, bidderPassword);
		Bidder bidder= null;
		try {
			bidder=query.getSingleResult();
		} catch (Exception e) {
		}
		return bidder;
	}

	public PlaceSellrequest findSellRequestById(int sellRequestId) {
		return em.find(PlaceSellrequest.class, sellRequestId);
	}

	public Bidder findBidderById(int bidderId) {
		return em.find(Bidder.class, bidderId);
	}

	@Transactional
	public void placeBid(Bidding bidding, int bidderId, int sellrequestId) {
		String jpql = "select b from Bidding b where b.bidder.bidderId=?1 and b.sellRequest.sellRequestId=?2";
		TypedQuery<Bidding> query = em.createQuery(jpql, Bidding.class);
		query.setParameter(1, bidderId);
		query.setParameter(2, sellrequestId);
		Bidding biddingCheck = null;

		try {
			biddingCheck = query.getSingleResult();
		} catch (Exception e) {
		}

		if (biddingCheck == null) {
			//// tx.begin();
			String jpql2 = "update Bidding b set b.biddingStatus='Old' where b.sellRequest.sellRequestId=?1 and b.biddingStatus='Previous'";
			Query q = em.createQuery(jpql2);
			q.setParameter(1, sellrequestId);
			q.executeUpdate();

			String jpql1 = "update Bidding b set b.biddingStatus='Previous' where b.sellRequest.sellRequestId=?1 and b.biddingStatus='Active'";
			q = em.createQuery(jpql1);
			q.setParameter(1, sellrequestId);
			q.executeUpdate();

			em.merge(bidding);

		} else {
			double biddingAmt = bidding.getBiddingAmount();

			int biddingId = biddingCheck.getBiddingId();

			String jpql2 = "update Bidding b set b.biddingStatus='Old' where b.sellRequest.sellRequestId=?1 and b.biddingStatus='Previous'";
			Query q = em.createQuery(jpql2);
			q.setParameter(1, sellrequestId);
			q.executeUpdate();

			String jpql1 = "update Bidding b set b.biddingStatus='Previous' where b.sellRequest.sellRequestId=?1 and b.biddingStatus='Active'";
			q = em.createQuery(jpql1);
			q.setParameter(1, sellrequestId);
			q.executeUpdate();

			jpql = "update Bidding b set b.biddingAmount=?1, b.biddingStatus='Active'  where b.bidder.bidderId=?2 and b.sellRequest.sellRequestId=?3";
			q = em.createQuery(jpql);
			q.setParameter(1, biddingAmt);
			q.setParameter(2, bidderId);
			q.setParameter(3, sellrequestId);

			q.executeUpdate();

		}
	}

	@Transactional
	public void startBidding(int sellRequestId) {
		String jpql = "update PlaceSellrequest p set p.isSold ='Unsold' where p.sellRequestId=:sid";
		Query q = em.createQuery(jpql);
		q.setParameter("sid", sellRequestId);
		q.executeUpdate();
	}

	@Transactional
	public Bidding stopBidding(int sellRequestId) {
		String jpql = "update PlaceSellrequest p set p.isSold ='sold' where p.sellRequestId=:sid";
		Query q = em.createQuery(jpql);
		q.setParameter("sid", sellRequestId);
		q.executeUpdate();

		jpql = "select b from Bidding b where b.biddingAmount=(select max(b1.biddingAmount) from Bidding b1 where b1.sellRequest.sellRequestId=?1) and b.sellRequest.sellRequestId=?1";
		q = em.createQuery(jpql);
		q.setParameter(1, sellRequestId);
		System.out.println(q.getSingleResult());
		
		
		return (Bidding) q.getSingleResult();

	}

	@Transactional
	public void addWinner(Winner winner) {
		em.persist(winner);
	}
	
	public String checkCropStatus(int sellrequestId) {
		String jpql = "select p.isSold from PlaceSellrequest p where p.sellRequestId=:sid";
		TypedQuery<String> qry = em.createQuery(jpql, String.class);
		qry.setParameter("sid", sellrequestId);
		String isSold = qry.getSingleResult();
		return isSold;
	}
	
	public String verifyBid(int bidderId,int sellRequestId) {
		String jpql="select b.biddingStatus from Bidding b where b.sellRequest.sellRequestId=?1 and b.bidder.bidderId=?2";
		TypedQuery<String> q= em.createQuery(jpql,String.class);
		q.setParameter(1, sellRequestId);
		q.setParameter(2, bidderId);
		String verify=null;
		try {
			verify=q.getSingleResult();
		} catch (Exception e) {
		}
		return verify;
	}
	
	public double checkBidAmount(int sellRequestId) {
		String jpql="select b.biddingAmount from Bidding b where b.sellRequest.sellRequestId=?1 and b.biddingStatus='Active'";
		TypedQuery<Double> q= em.createQuery(jpql,Double.class);
		q.setParameter(1, sellRequestId);
		double previousBid=0;
		try {
			previousBid=q.getSingleResult();
		} catch (Exception e) {
		}
		return previousBid;
	}

	//winners ki list ke liye
	public List<Winner> getWinnerByBidderId(int bidderId) {
		String jpql="select w from Winner w where w.bidding.bidder.bidderId=?1";
		TypedQuery<Winner> q= em.createQuery(jpql,Winner.class);
		q.setParameter(1, bidderId);
		List<Winner> winners=null;
		try {
			winners=q.getResultList();
		} catch (Exception e) {
		}
		
		return winners;
	}
	
	public Bidder getBidderByFarmerId(int farmerId) {
		String jpql="select w.bidding.bidder from Winner w where w.bidding.sellRequest.farmer.farmerId=?1";
		TypedQuery<Bidder> q= em.createQuery(jpql,Bidder.class);
		q.setParameter(1, farmerId);
		Bidder winner=null;
		try {
			winner=q.getSingleResult();
		} catch (Exception e) {
		}
		System.out.println(winner.getBidderId());
		return winner;
	}
	
	public Bidder getWinnerBySellRequestId(int sellRequestId) {
		String jpql="select w.bidding.bidder from Winner w where w.bidding.sellRequest.sellRequestId=?1";
		TypedQuery<Bidder> q= em.createQuery(jpql,Bidder.class);
		q.setParameter(1, sellRequestId);
		Bidder winner=null;
		try {
			winner=q.getSingleResult();
		} catch (Exception e) {
		}
		System.out.println(winner.getBidderId());
		return winner;
	}
	
	public Bidding getWinnerByBidding(int sellRequestId) {
        String jpql="select w.bidding from Winner w where w.bidding.sellRequest.sellRequestId=?1";
        TypedQuery<Bidding> q= em.createQuery(jpql,Bidding.class);
        q.setParameter(1, sellRequestId);
        Bidding winner=null;
        try {
            winner=q.getSingleResult();
        } catch (Exception e) {
        }
        System.out.println(winner.getBiddingId());
        return winner;
	}
	
	//address ki list ke liye
	public List<PlaceSellrequest> findSellRequestByFarmerId(int farmerId){
		String jpql="select s from PlaceSellrequest s where s.isSold='sold' and s.farmer.farmerId=:fId";
		TypedQuery<PlaceSellrequest> q= em.createQuery(jpql,PlaceSellrequest.class);
        q.setParameter("fId", farmerId);
        List<PlaceSellrequest> sellrequests=null;
        try {
            sellrequests=q.getResultList();
        } catch (Exception e) {
        }
//        System.out.println(sellrequests.getBiddingId());
        return sellrequests;
	}

	public Crop getCropByName() {
		// TODO Auto-generated method stub
		return null;
	}
}
