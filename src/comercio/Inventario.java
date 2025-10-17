package comercio;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cliente.Cartao;
import cliente.CupaoProdutos;

/**
 * Classe que representa o inventário da empresa, desde os produtos, até aos
 * cartões e cupões. Responsável por armazenar todos essses dados, bem como
 * fornecer métodos de pesquisa que usem os códigos para descobrir os produtos.
 */
public class Inventario {

    private Map<String, ProdutoInfo> produtos = new HashMap<>();
    private Map<String, Cartao> cartoes = new HashMap<>();
    private Map<String, CupaoProdutos> cupoes = new HashMap<>();

    public void addProduto(ProdutoInfo p) {
        produtos.put(p.getCodigoBarras(), Objects.requireNonNull(p));
    }

    public void removeProduto(ProdutoInfo p) {
        produtos.remove(p.getCodigoBarras());
    }

    /**
     * Retorna qual o produto que tem um dado código de barras
     * 
     * @param codigoBarras o código de barras do produto que se pretende
     * @return o produto com o código de barras, ou null caso não exista
     */
    public ProdutoInfo getProduto(String codigoBarras) {
        return produtos.get(codigoBarras);
    }

    public Collection<ProdutoInfo> getProdutos() {
        return Collections.unmodifiableCollection(produtos.values());
    }

    public void addCartao(Cartao c) {
        cartoes.put(c.getNumero(), Objects.requireNonNull(c));
    }

    public void removeCartao(Cartao c) {
        produtos.remove(c.getNumero());
    }

    /**
     * Retorna o cartão com um dado número
     * 
     * @param numero o número do cartão a procurar
     * @return o cartão com o número pedido, ou null caso não exista
     */
    public Cartao getCartao(String numero) {
        return cartoes.get(numero);
    }

    public Collection<Cartao> getCartoes() {
        return Collections.unmodifiableCollection(cartoes.values());
    }

    public void addCupao(CupaoProdutos c) {
        cupoes.put(c.getNumero(), c);
    }

    public void removeCupao(CupaoProdutos c) {
        cupoes.remove(c.getNumero());
    }

    /**
     * Retorna o cupão com um dado número
     * 
     * @param numero o número do cupão a procurar
     * @return o cupão com o número pedido, ou null caso não exista
     */
    public CupaoProdutos getCupao(String numero) {
        return cupoes.get(numero);
    }

    public Collection<CupaoProdutos> getCupoes() {
        return Collections.unmodifiableCollection(cupoes.values());
    }

    public void vendeProduto(Venda v, String codigoBarras) {
        // TODO acabar este método. Deve pegar no produto com o código de barras e
        // colocá-lo na venda
    }
}
