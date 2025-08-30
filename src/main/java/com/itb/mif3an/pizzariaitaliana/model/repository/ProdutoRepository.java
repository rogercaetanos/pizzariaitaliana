package com.itb.mif3an.pizzariaitaliana.model.repository;


import com.itb.mif3an.pizzariaitaliana.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
