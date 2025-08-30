package com.itb.mif3an.pizzariaitaliana.model.repository;


import com.itb.mif3an.pizzariaitaliana.model.entity.Admin;
import com.itb.mif3an.pizzariaitaliana.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
