package com.cyber08.uniclub.respository;

import com.cyber08.uniclub.entity.Varient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarientRepository extends JpaRepository<Varient, Integer> {
}
