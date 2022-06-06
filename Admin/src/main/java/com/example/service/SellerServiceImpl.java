package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.Admin1Controller;
import com.example.domain.Product;
import com.example.domain.Review;
import com.example.domain.Sellerid;
import com.example.persistence.ProductRepository;
import com.example.persistence.ReviewRepository;
import com.example.persistence.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService{
	
	static final Logger logger = LoggerFactory.getLogger(Admin1Controller.class);
	
	@Autowired
	SellerRepository sellerRepo;
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	//판매자 전체 리스트
	@Override
	public List<Sellerid> getSellerList(Sellerid sid) {
		logger.info("판매자 전체 리스트");
		return (List<Sellerid>)sellerRepo.findAll();
		
	}
	
	@Override
	public Sellerid sellerdetail(Sellerid sid) {
		logger.info("판매자 상세보기");
		return sellerRepo.findById(sid.getBcode()).get();
	}

	//판매자 가게 정보보기 (민지)
	@Override
	public Sellerid getshopInfo(Sellerid sid) {
		logger.info("판매자가게 정보보기");
		return sellerRepo.findById(sid.getBcode()).get();
	}
	
	//판매자 상품 리스트 조회하기
	@Override
	public List<Product> getProList(Product pr) {
		return (List<Product>)productRepo.findAll();
	}
	
	//판매자 상품 조회하기
	@Override
	public Product getshopProDetails(Product pr) {
		logger.info("판매자상품 정보보기");
		return productRepo.findById(pr.getPcode()).get();
	}
		
	//판매자 상품 등록하기
	@Override
	public void insertPro(Product pr) {
		productRepo.save(pr);
	}
	
	//판매자 상품 수정하기
	@Override
	public void updatePro(Product pr) {
		//boardRepo.save(vo); 
		
		// 기본키값에 의한 엔티티 검색
		Product su = productRepo.findById(pr.getPcode()).get();
		
		// 검색된 엔티티에 각각의 값을 변경
		su.setPname(pr.getPname());
		su.setPprice(pr.getPprice());
		su.setPcontent(pr.getPcontent());
		
		// save(검색된 엔티티)
		productRepo.save(su);
	}

	//판매가 가게 리뷰보기
	@Override
	public List<Review> getReviewList(Review re) {
		return (List<Review>)reviewRepo.findAll();
	}
}
