package com.itb.mif3an.pizzariaitaliana.model.repository;



import com.itb.mif3an.pizzariaitaliana.model.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
