package com.example.shoppurchase.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.shoppurchase.domain.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, String> {

	Page<Purchase> findByUserId(String userId, Pageable pageRequest);
	
	//Page<Purchase> findByProductId(String productId, Pageable pageRequest);
	String findByProductId(String productId);
	
	@Query(value="SELECT user_id FROM purchase WHERE product_id = ?1", nativeQuery = true)
	Page<String> prova(String productId, Pageable pageRequest);
}
