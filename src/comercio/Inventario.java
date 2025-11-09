package comercio;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cliente.Cartao;
import cliente.CupaoProdutos;

/**
 * Classe que representa o inventário da empresa HonESTa.
 * 
 * É o "armazém central" que guarda:
 * - Todos os produtos disponíveis
 * - Todos os cupões existentes
 * - Todos os cartões de clientes
 * 
 * USA HASHMAP:
 * Usa HashMap para busca rápida por código/número.
 * Muito mais rápido que ArrayList quando precisamos
 * encontrar algo específico!
 * 
 * EXEMPLO DE USO:
 * - getProduto("202-006") → busca instantânea!
 * - getCartao("10101") → busca instantânea!
 * - getCupao("1001") → busca instantânea!
 */
public class Inventario {
    
    // ========================================================================
    // CAMPOS - Inicializados na declaração (FORMA 1)
    // ========================================================================
    
    private Map<String, ProdutoInfo> produtos = new HashMap<>();
    private Map<String, Cartao> cartoes = new HashMap<>();
    private Map<String, CupaoProdutos> cupoes = new HashMap<>();
    
    
    // ========================================================================
    // MÉTODOS PARA PRODUTOS
    // ========================================================================
    
    /**
     * Adiciona um produto ao inventário.
     * 
     * @param p o produto a adicionar
     * @throws NullPointerException se produto for null
     */
    public void addProduto(ProdutoInfo p) {
        Objects.requireNonNull(p, "Produto não pode ser null");
        produtos.put(p.getCodigoBarras(), p);
    }
    
    /**
     * Remove um produto do inventário.
     * 
     * @param p o produto a remover
     */
    public void removeProduto(ProdutoInfo p) {
        if (p != null) {
            produtos.remove(p.getCodigoBarras());
        }
    }
    
    /**
     * Retorna qual o produto que tem um dado código de barras.
     * 
     * @param codigoBarras o código de barras do produto que se pretende
     * @return o produto com o código de barras, ou null caso não exista
     */
    public ProdutoInfo getProduto(String codigoBarras) {
        return produtos.get(codigoBarras);
    }
    
    /**
     * Retorna todos os produtos do inventário.
     * 
     * @return coleção não modificável de todos os produtos
     */
    public Collection<ProdutoInfo> getProdutos() {
        return Collections.unmodifiableCollection(produtos.values());
    }
    
    
    // ========================================================================
    // MÉTODOS PARA CARTÕES
    // ========================================================================
    
    /**
     * Adiciona um cartão ao inventário.
     * 
     * @param c o cartão a adicionar
     * @throws NullPointerException se cartão for null
     */
    public void addCartao(Cartao c) {
        Objects.requireNonNull(c, "Cartão não pode ser null");
        cartoes.put(c.getNumero(), c);
    }
    
    /**
     * Remove um cartão do inventário.
     * 
     * @param c o cartão a remover
     */
    public void removeCartao(Cartao c) {
        if (c != null) {
            cartoes.remove(c.getNumero());
        }
    }
    
    /**
     * Retorna o cartão com um dado número.
     * 
     * @param numero o número do cartão a procurar
     * @return o cartão com o número pedido, ou null caso não exista
     */
    public Cartao getCartao(String numero) {
        return cartoes.get(numero);
    }
    
    /**
     * Retorna todos os cartões do inventário.
     * 
     * @return coleção não modificável de todos os cartões
     */
    public Collection<Cartao> getCartoes() {
        return Collections.unmodifiableCollection(cartoes.values());
    }
    
    
    // ========================================================================
    // MÉTODOS PARA CUPÕES
    // ========================================================================
    
    /**
     * Adiciona um cupão ao inventário.
     * 
     * @param c o cupão a adicionar
     * @throws NullPointerException se cupão for null
     */
    public void addCupao(CupaoProdutos c) {
        Objects.requireNonNull(c, "Cupão não pode ser null");
        cupoes.put(c.getNumero(), c);
    }
    
    /**
     * Remove um cupão do inventário.
     * 
     * @param c o cupão a remover
     */
    public void removeCupao(CupaoProdutos c) {
        if (c != null) {
            cupoes.remove(c.getNumero());
        }
    }
    
    /**
     * Retorna o cupão com um dado número.
     * 
     * @param numero o número do cupão a procurar
     * @return o cupão com o número pedido, ou null caso não exista
     */
    public CupaoProdutos getCupao(String numero) {
        return cupoes.get(numero);
    }
    
    /**
     * Retorna todos os cupões do inventário.
     * 
     * @return coleção não modificável de todos os cupões
     */
    public Collection<CupaoProdutos> getCupoes() {
        return Collections.unmodifiableCollection(cupoes.values());
    }
    
    
    // ========================================================================
    // MÉTODOS ESPECIAIS
    // ========================================================================
    
    /**
     * Adiciona um produto à venda usando o código de barras.
     * 
     * @param v a venda onde adicionar o produto
     * @param codigoBarras o código de barras do produto
     * @throws IllegalArgumentException se produto não existe
     */
    public void vendeProduto(Venda v, String codigoBarras) {
        ProdutoInfo produto = getProduto(codigoBarras);
        
        if (produto == null) {
            throw new IllegalArgumentException(
                "Produto com código " + codigoBarras + " não existe no inventário!");
                // CORREÇÕES: ↑ acento    ↑ espaço antes       ↑ "inventário" correto
        }
        
        ProdutoVendido pv = new ProdutoVendido(produto, produto.getPreco());
        v.adicionarProduto(pv);
    }
}