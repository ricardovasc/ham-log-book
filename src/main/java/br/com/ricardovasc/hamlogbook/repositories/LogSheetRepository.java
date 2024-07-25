package br.com.ricardovasc.hamlogbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import br.com.ricardovasc.hamlogbook.models.LogSheet;

@Repository
public interface LogSheetRepository extends JpaRepository<LogSheet, Integer> {

    void deleteById(@NonNull Integer id);
}
