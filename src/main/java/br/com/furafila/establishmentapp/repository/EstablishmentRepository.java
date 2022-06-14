package br.com.furafila.establishmentapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.furafila.establishmentapp.model.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

	@Query("select e from EstablishmentLogin el inner join el.establishment e where el.login.id = :loginId")
	Optional<Establishment> findInitialInfo(@Param("loginId") Long loginId);

}
