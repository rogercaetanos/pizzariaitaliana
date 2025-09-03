package com.itb.mif3an.pizzariaitaliana.model.services;


import com.itb.mif3an.pizzariaitaliana.model.entity.Produto;

import java.util.List;

public interface ProdutoService {

    public Produto findById(Long id);
    public List<Produto> findAll();
    public Produto save(Produto produto);
    public void delete(Long id);
    public Produto update(Produto produto, Long id);
}
