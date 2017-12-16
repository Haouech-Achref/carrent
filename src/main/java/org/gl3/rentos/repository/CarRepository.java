package org.gl3.rentos.repository;

import org.gl3.rentos.model.Car;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,Integer> {
}
