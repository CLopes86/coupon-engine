package cliente;

import java.time.LocalDate;
import comercio.ProdutoVendido;

/**
 * Cupão que dá desconto em TODA a compra.
 * 
 * HERANÇA:
 * Esta classe ESTENDE (extends) Cupao.
 * Herda todos os campos e métodos da classe pai.
 * 
 * CARACTERÍSTICAS:
 * - Não tem campos adicionais
 * - Abrange TODOS os produtos
 * - Implementa o método abstrato abrange()
 * 
 * EXEMPLO DE USO:
 * Cupão "5% em qualquer compra" → Aplica-se a tudo!
 */

public class CupaoCompra extends Cupao{


     // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    /**
     * Cria um cupão de compra.
     * 
     * SUPER:
     * Chama o construtor da classe pai (Cupao).
     * Passa todos os parâmetros para o construtor protected de Cupao.
     * 
     * @param numero número do cupão
     * @param resumo descrição
     * @param desconto percentagem (0.0 a 1.0)
     * @param inicio data início
     * @param fim data fim
     */
    public CupaoCompra(String numero, String resumo, float desconto, LocalDate inicio, LocalDate fim) {
        super(numero, resumo, desconto, inicio, fim);
        //TODO Auto-generated constructor stub
    }

     // ========================================================================
    // IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
    // ========================================================================
    
    /**
     * Verifica se este cupão abrange o produto.
     * 
     * CupaoCompra abrange TODOS os produtos!
     * Por isso sempre retorna true.
     * 
     * OVERRIDE:
     * Este método implementa o método abstrato da classe pai.
     * @Override indica que estamos a sobrescrever um método.
     * 
     * @param p o produto (não usado, pois abrange todos)
     * @return sempre true
     */
    @Override
    public boolean abrange(ProdutoVendido p) {
        return true;
    }

     // ========================================================================
    // toString - Opcional, mas útil
    // ========================================================================
    
    /**
     * Representação textual específica para CupaoCompra.
     * 
     * @return string com informação do cupão de compra
     */
    @Override
    public String toString() {
        return String.format("CupaoCompra[%s] %s - %.0f%%",
            getNumero(),
            getResumo(),
            getDesconto() * 100);
    }
}
    

