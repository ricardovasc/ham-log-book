package br.com.ricardovasc.hamlogbook.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ricardovasc.hamlogbook.model.Callsign;


@Repository
public interface CallsignRepository extends JpaRepository<Callsign, Integer> {

    Optional<Callsign> findByCode(String code);
}
