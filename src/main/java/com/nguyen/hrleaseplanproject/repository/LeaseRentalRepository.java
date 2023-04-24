package com.nguyen.hrleaseplanproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyen.hrleaseplanproject.model.LeaseRental;

@Repository
public interface LeaseRentalRepository extends JpaRepository<LeaseRental, Long>{
	
}
