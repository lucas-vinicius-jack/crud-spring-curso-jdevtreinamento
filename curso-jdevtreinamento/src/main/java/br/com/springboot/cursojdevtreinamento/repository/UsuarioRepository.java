package br.com.springboot.cursojdevtreinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.cursojdevtreinamento.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT u FROM Usuario u WHERE upper(trim(u.nome)) LIKE %?1%")
	List<Usuario> buscarProNome(String name);
		
}   