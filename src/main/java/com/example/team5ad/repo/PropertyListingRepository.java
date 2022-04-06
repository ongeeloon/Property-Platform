package com.example.team5ad.repo;


import com.example.team5ad.entity.PropertyListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*Used by EeLoon*/
@Repository
public interface PropertyListingRepository extends JpaRepository<PropertyListing, Long> {

    //Get all
    List<PropertyListing> findAll();

    //Get all with pages
    Page<PropertyListing> findAll(Pageable pageable);

    //Search by address keyword
    @Query(value="select * from property_listing p where p.address like %:keyword%", nativeQuery = true)
    List<PropertyListing> findByKeyword(@Param("keyword") String keyword);

    @Query(value="select * from property_listing p where p.address like %:keyword%", nativeQuery = true)
    Page<PropertyListing> findByKeyword2(@Param("keyword") String keyword, Pageable pageable);

    //NEW! added by XH
    @Query(value="select * from property_listing p where p.user_id = ?1", nativeQuery = true)
    List<PropertyListing>findPropertyListingByUserId(Long userId);
}
