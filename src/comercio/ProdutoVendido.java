package comercio;

import cliente.CupaoProdutos;

/**
 * Representa um produto vendido numa compra.
 */
public class ProdutoVendido {
    
    private ProdutoInfo info;
    private long preco;
    private CupaoProdutos cupao;
    
    public ProdutoVendido(ProdutoInfo info, long preco) {
        this.info = info;
        this.preco = preco;
        this.cupao = null;
    }
    
    /**
     * Retorna o preço.
     */
    public float getPreco() {
        return preco;
    }

    /**
     * Define o cupão.
     */
    public void setCupao(CupaoProdutos cupaoProdutos) {
        this.cupao = cupaoProdutos;
    }

    /**
     * Retorna o cupão.
     */
    public CupaoProdutos getCupao() {
        return cupao;
    }

    /**
     * Retorna a informação do produto.
     */
    public ProdutoInfo getInfo() {
        return info;
    }
}