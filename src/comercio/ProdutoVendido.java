package comercio;

import cliente.Cupao;
import cliente.CupaoProdutos;


/**
 * Representa um produto vendido numa compra específica.
 * 
 * DIFERENÇA IMPORTANTE:
 * - ProdutoInfo = informação geral do produto (na prateleira)
 * - ProdutoVendido = produto numa venda específica (no carrinho)
 * 
 * COMPOSIÇÃO:
 * Esta classe USA ProdutoInfo (tem um ProdutoInfo dentro).
 * Isto evita duplicar código e permite reutilizar informação.
 * 
 * RESPONSABILIDADES:
 * - Guardar qual produto foi vendido (através de ProdutoInfo)
 * - Guardar o preço DESTA venda específica (pode ser diferente!)
 * - Guardar o cupão aplicado (se houver)
 */
public class ProdutoVendido {
    
    // ========================================================================
    // CAMPOS
    // ========================================================================
    
    /**
     * Informação geral do produto (código, marca, modelo, preço original).
     * Usa COMPOSIÇÃO - tem um ProdutoInfo dentro.
     */
    private ProdutoInfo info;
    
    /**
     * Preço DESTA venda específica (em cêntimos).
     * Pode ser diferente do preço original se houver promoção!
     * 
     * EXEMPLO:
     * - info.getPreco() = 231 (preço original: 2.31€)
     * - this.preco = 150 (preço nesta venda: 1.50€ - promoção!)
     */
    private long preco;
    
    /**
     * Cupão aplicado a este produto nesta venda.
     * null = não tem cupão aplicado
     * Vamos implementar cupões mais tarde!
     */
    private Cupao cupao;
    
    
    // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    /**
     * Cria um produto vendido.
     * 
     * NOTA: O cupão começa como null (sem cupão).
     *       É aplicado depois através de setCupao().
     * 
     * @param info informação geral do produto
     * @param preco preço DESTA venda (pode ser diferente do original)
     */
    public ProdutoVendido(ProdutoInfo info, long preco) {
        this.info = info;
        this.preco = preco;
        this.cupao = null;  // Inicialmente sem cupão
    }
    
    
    // ========================================================================
    // GETTERS
    // ========================================================================
    
    /**
     * Retorna a informação geral do produto.
     * 
     * COMPOSIÇÃO EM AÇÃO:
     * Retorna o objeto ProdutoInfo inteiro!
     * Depois pode aceder aos métodos dele:
     *   vendido.getInfo().getMarca()
     *   vendido.getInfo().getModelo()
     * 
     * @return o ProdutoInfo com código, marca, modelo, etc.
     */
    public ProdutoInfo getInfo() {
        return info;
    }
    
    /**
     * Retorna o preço DESTA venda.
     * 
     * ATENÇÃO: Este é o preço DESTA venda, não o original!
     * Para o preço original: getInfo().getPreco()
     * 
     * @return o preço em cêntimos
     */
    public long getPreco() {
        return preco;
    }
    
    /**
     * Retorna o cupão aplicado.
     * 
     * @return o cupão (ou null se não tem cupão)
     */
    public Cupao getCupao() {
        return cupao;
    }
    
    
    // ========================================================================
    // SETTER
    // ========================================================================
    
    /**
     * Define o cupão aplicado a este produto.
     * 
     * Chamado durante o processamento da venda quando
     * o sistema verifica se há cupões aplicáveis.
     * 
     * @param c o cupão a aplicar (pode ser null para remover)
     */
    public void setCupao(Cupao c ) {
        this.cupao = c;
    }
    
    
    // ========================================================================
    // MÉTODOS AUXILIARES
    // ========================================================================
    
    /**
     * Retorna o preço em euros (convertido de cêntimos).
     * 
     * @return o preço em euros
     */
    public double getPrecoEmEuros() {
        return preco / 100.0;
    }
    
    /**
     * Verifica se este produto tem cupão aplicado.
     * 
     * @return true se tem cupão, false se não tem
     */
    public boolean temCupao() {
        return cupao != null;
    }
    
    
    // ========================================================================
    // toString
    // ========================================================================
    
    /**
     * Retorna uma representação textual do produto vendido.
     * 
     * @return string com informação do produto vendido
     */
    @Override
    public String toString() {
        String cupaoInfo = temCupao() ? " [COM CUPÃO]" : "";
        return String.format("ProdutoVendido: %s - %.2f€%s", 
            info.getModelo(), 
            getPrecoEmEuros(),
            cupaoInfo);
    }
}