package br.com.furafila.establishmentapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.furafila.establishmentapp.model.EstablishmentLogin;

public interface EstablishmentLoginRepository extends JpaRepository<EstablishmentLogin, Long> {

	@Query("select el from EstablishmentLogin el inner join el.login l where el.establishment.id = :establishmentId and el.login.id != :loginId")
	List<EstablishmentLogin> listEstablishmentUsers(@Param("establishmentId") Long establishmentId,
			@Param("loginId") Long loginId);

	Optional<EstablishmentLogin> findByLoginId(Long loginId);

}
