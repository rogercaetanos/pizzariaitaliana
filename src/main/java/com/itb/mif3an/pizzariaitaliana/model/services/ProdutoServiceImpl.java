package com.itb.mif3an.pizzariaitaliana.model.services;

import com.itb.mif3an.pizzariaitaliana.exceptions.NotFound;
import com.itb.mif3an.pizzariaitaliana.model.entity.Produto;
import com.itb.mif3an.pizzariaitaliana.model.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto findById(Long id) {
        try{
            return produtoRepository.findById(id).get();
        } catch (Exception e) {
            throw new NotFound("Produto não encontrado com o id " + id);
        }
    }

    @Override
    public List<Produto> findAll() {
        return this.produtoRepository.findAll();
    }

    @Override
    public Produto save(Produto produto) {
        produto.setCodStatus(true);
        // É possível salvar o produto sem categoria, pois a chave estrangeira é NULL
        /*
        if(produto.getCategoria() != null){
            Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
            if(categoria == null){
                throw new BadRequest("Não foi encontrado a categoria com o id " + produto.getCategoria().getId());
            }
        } */
        return produtoRepository.save(produto);
    }

    @Override
    public void delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto, Long id) {
        if(!produtoRepository.existsById(id)){
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        Produto produtoAtual = produtoRepository.findById(id).get();
        produtoAtual.setNome(produto.getNome());
        produtoAtual.setDescricao(produto.getDescricao());
        produtoAtual.setTipo(produto.getTipo());
        produtoAtual.setValorVenda(produto.getValorVenda());
        produtoAtual.setValorCompra(produto.getValorCompra());
        produtoAtual.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoAtual.setCodStatus(produto.isCodStatus());
         /*
        if(produto.getCategoria() != null){
            Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
            if(categoria == null){
                throw new NotFound("Não foi encontrado a categoria com o id " + produto.getCategoria().getId());
            }
            produtoAtual.setCategoria(produto.getCategoria());
        } */
        return produtoRepository.save(produtoAtual);
    }
}
