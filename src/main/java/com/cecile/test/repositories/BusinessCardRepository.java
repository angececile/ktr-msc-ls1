package com.cecile.test.repositories;

import com.cecile.test.domaine.BusinessCard;
import com.cecile.test.domaine.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessCardRepository extends JpaRepository<BusinessCard,Integer> {
    List<BusinessCard> findAllByProfile(Profile profile);
}
