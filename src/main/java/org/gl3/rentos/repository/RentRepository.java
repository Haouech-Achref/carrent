package org.gl3.rentos.repository;
import org.gl3.rentos.model.Rent;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface RentRepository extends CrudRepository<Rent,Integer> {
    public ArrayList<Rent> findAllByPickupBetweenAndDropoffBetween(Date pickupbegin, Date pickupend, Date dropoffbegin, Date dropoffend );
}
