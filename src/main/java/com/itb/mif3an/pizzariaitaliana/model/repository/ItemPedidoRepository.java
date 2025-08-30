package com.itb.mif3an.pizzariaitaliana.model.repository;


import com.itb.mif3an.pizzariaitaliana.model.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
