package br.com.furafila.establishmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.furafila.establishmentapp.model.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

}
