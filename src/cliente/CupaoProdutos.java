package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comercio.ProdutoInfo;
import comercio.ProdutoVendido;
import util.Validator;


/**
 * Classe que representa um cupão de produtos emitido pela cadeia de lojas HonESta.
 
 * HERANÇA:
 * - extends Cupao → É UM cupão
 * - Herda: numero, resumo, desconto, inicio, fim, estaValido()
 * - Adiciona: lista de produtos abrangidos
 * - Implementa: método abstrato abrange()
 */

public class CupaoProdutos extends Cupao {

    // ========================================================================
    // CAMPOS ESPECÍFICOS
    // ========================================================================
    
    /**
     * Lista de produtos abrangidos por este cupão.
     * 
     * ÚNICO CAMPO DESTA CLASSE!
     * Os outros (numero, resumo, desconto, inicio, fim) estão no PAI.
     */
    private List<ProdutoInfo> abrangidos = new ArrayList<>();

     // ========================================================================
    // CONSTRUTORES
    // ========================================================================
    
    /**
     * Construtor com lista de produtos inicial.
     * 
     * @param numero número do cupão
     * @param resumo descrição do cupão
     * @param abrangidos lista de produtos abrangidos
     * @param desconto percentagem de desconto (0.0 a 1.0)
     * @param ini data de início da validade
     * @param fim data de fim da validade
     */
    public CupaoProdutos(String numero, String resumo, List<ProdutoInfo> abrangidos, float desconto, LocalDate inicio, LocalDate fim) {
        super(numero, resumo, desconto, inicio, fim);
        this.abrangidos.addAll(abrangidos);
    }

     /**
     * Construtor sem produtos (lista vazia).
     * Chama o outro construtor passando lista vazia.
     * 
     * @param numero número do cupão
     * @param resumo descrição
     * @param desconto percentagem (0.0 a 1.0)
     * @param ini data início
     * @param fim data fim
     */
    public CupaoProdutos(String numero, String resumo, float desconto, LocalDate inicio, LocalDate fim){
        this(numero, resumo, new ArrayList<ProdutoInfo>(), desconto, inicio, fim);
    }

      // ========================================================================
    // MÉTODOS PARA GERIR PRODUTOS
    // ========================================================================
    public void addProduto(ProdutoInfo p){
        abrangidos.add(Objects.requireNonNull(p));
    }
    public void removeProduto(ProdutoInfo p){
        abrangidos.remove(p);
    }

    public List<ProdutoInfo> getAbrangidos() {
        return Collections.unmodifiableList(abrangidos);
    }


     // ========================================================================
    // IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
    // ========================================================================
    
    /**
     * Verifica se este cupão abrange um dado produto.
     * 
     * 
     * 1. Verifica se produto já tem cupão com desconto maior
     * 2. Verifica se produto está na lista de abrangidos
     * 
     * @Override:
     * Este método IMPLEMENTA o método abstrato do pai (Cupao).
     * O pai declarou: public abstract boolean abrange(ProdutoVendido p);
     * Aqui fornecemos a implementação ESPECÍFICA para produtos.
     * 
     * @param p o produto a verificar
     * @return true se cupão abrange o produto
     */
    @Override
    public boolean abrange(ProdutoVendido p) {
        return (p.getCupao() == null || p.getCupao().getDesconto() < getDesconto())
        && abrangidos.contains(p.getInfo());
    }

     // ========================================================================
    // MÉTODO AUXILIAR
    // ========================================================================
    
    /**
     * Método auxiliar para aplicar o cupão a um produto.
     * 
     * @param c o cartão onde acumular o saldo
     * @param p o produto a ser usado
     * @return true se aplicou com sucesso
     */
    public boolean aplicar(Cartao c, ProdutoVendido p) {
    if (!abrange(p))
        return false;
    c.acumularSaldo((long) (p.getPreco() * this.getDesconto()));
    //                                      ↑
    //                          ESTE cupão (this)
    p.setCupao(this);
    return true;
}
    
}
