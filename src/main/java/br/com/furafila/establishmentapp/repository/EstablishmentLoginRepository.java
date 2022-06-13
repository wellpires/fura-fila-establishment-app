package br.com.furafila.establishmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.furafila.establishmentapp.model.EstablishmentLogin;

public interface EstablishmentLoginRepository extends JpaRepository<EstablishmentLogin, Long> {

}
