package br.com.testege21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testege21.model.Geo;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Long> {

}
