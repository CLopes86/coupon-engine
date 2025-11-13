package cliente;

import java.time.LocalDate;
import comercio.ProdutoVendido;
import util.Validator;
/**
 * Cupão que dá desconto em produtos de uma MARCA específica.
 * 
 * HERANÇA:
 * - Herda de Cupao (classe abstrata)
 * - Herda campos: numero, resumo, desconto, inicio, fim
 * - Herda métodos: estaValido(), getters
 * - Implementa: abrange() de forma específica
 * 
 * CARACTERÍSTICAS:
 * - Tem UMA marca (String)
 * - Aplica-se a TODOS os produtos dessa marca
 * - Verifica pela marca do produto
 * 
 * EXEMPLO:
 * Cupão "10% em produtos Albicereal"
 * - Abrange: Massa Albicereal ✅, Arroz Albicereal ✅
 * - NÃO abrange: Chocolate Nestlé ❌, Leite Mimosa ❌
 */
public class CupaoMarca extends Cupao {

    // ========================================================================
    // CAMPO ESPECÍFICO
    // ========================================================================
    
    /**
     * Marca dos produtos abrangidos por este cupão.
     * 
     * ÚNICO CAMPO DESTA CLASSE!
     * Os outros estão no PAI (Cupao).
     * 
     * Exemplos: "Albicereal", "Nestlé", "Continente"
     */
    private String marca;


     // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    /**
     * Cria um cupão de marca.
     * 
     * PARÂMETRO EXTRA:
     * Além dos parâmetros do pai, tem também "marca".
     * 
     * @param numero número do cupão
     * @param resumo descrição do cupão
     * @param desconto percentagem de desconto (0.0 a 1.0)
     * @param inicio data de início da validade
     * @param fim data de fim da validade
     * @param marca marca dos produtos abrangidos
     * @throws IllegalArgumentException se marca for vazia
     */
    public CupaoMarca(String numero, String resumo, float desconto, LocalDate inicio, LocalDate fim, String marca) {
        super(numero, resumo, desconto, inicio, fim);
        this.marca = Validator.requireNonBlank(marca);
    }

       // ========================================================================
    // GETTER
    // ========================================================================
    public String getMarca(){
        return marca;
    }

      // ========================================================================
    // IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
    // ========================================================================
    
    /**
     * Verifica se este cupão abrange o produto.
     * 
     * LÓGICA:
     * Compara a marca do produto com a marca do cupão.
     * Usa equalsIgnoreCase() para ignorar maiúsculas/minúsculas.
     * 
     * FLUXO:
     * 1. Valida se produto não é null
     * 2. Obtém marca do produto
     * 3. Compara com marca do cupão (ignorando capitalização)
     * 
     * EXEMPLOS:
     * Cupão tem marca "Albicereal":
     * - Produto "Albicereal" → true ✅
     * - Produto "albicereal" → true ✅ (ignora maiúsculas)
     * - Produto "ALBICEREAL" → true ✅ (ignora maiúsculas)
     * - Produto "Nestlé" → false ❌ (marca diferente)
     * 
     * @param p o produto a verificar
     * @return true se a marca do produto coincide com a marca do cupão
     */
    @Override
    public boolean abrange(ProdutoVendido p) {
        if(p == null || p.getInfo() == null) {
            return false;
        }

        String marcaProduto = p.getInfo().getMarca();
        return this.marca.equalsIgnoreCase(marcaProduto);
    }
    
    
}
