package comercio;

/**
 * Representa um produto da loja HonESTa.
 * 
 * Esta classe guarda informação sobre produtos:
 * - Código de barras (identificação única)
 * - Marca (fabricante)
 * - Modelo (nome/descrição)
 * - Preço (em cêntimos)
 */
public class ProdutoInfo {
    
    // ========================================================================
    // CAMPOS - Dados que cada produto tem
    // ========================================================================
    
    private String codigoBarras;  // Ex: "202-006"
    private String marca;          // Ex: "Albicereal"
    private String modelo;         // Ex: "Massa Esparguete"
    private long preco;            // Ex: 231 (= 2.31€)
    
    
    // ========================================================================
    // CONSTRUTOR - Cria um novo produto
    // ========================================================================
    
    /**
     * Cria um novo produto com validações.
     * 
     * @param codigoBarras código único do produto (não pode ser vazio)
     * @param marca marca do produto (não pode ser vazia)
     * @param modelo nome do produto (não pode ser vazio)
     * @param preco preço em cêntimos (não pode ser negativo)
     * @throws IllegalArgumentException se algum parâmetro for inválido
     */
    public ProdutoInfo(String codigoBarras, String marca, String modelo, long preco) {
        
        // Validação 1: Código não pode ser vazio
        if (codigoBarras == null || codigoBarras.isBlank()) {
            throw new IllegalArgumentException("Código de barras não pode ser vazio!");
        }
        
        // Validação 2: Marca não pode ser vazia
        if (marca == null || marca.isBlank()) {
            throw new IllegalArgumentException("Marca não pode ser vazia!");
        }
        
        // Validação 3: Modelo não pode ser vazio
        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Modelo não pode ser vazio!");
        }
        
        // Validação 4: Preço não pode ser negativo
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        
        // Se passou todas as validações, atribui os valores
        this.codigoBarras = codigoBarras;
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
    }
    
    
    // ========================================================================
    // GETTERS - Consultar informação
    // ========================================================================
    
    /**
     * Retorna o código de barras do produto.
     * @return o código de barras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }
    
    /**
     * Retorna a marca do produto.
     * @return a marca
     */
    public String getMarca() {
        return marca;
    }
    
    /**
     * Retorna o modelo/nome do produto.
     * @return o modelo
     */
    public String getModelo() {
        return modelo;
    }
    
    /**
     * Retorna o preço do produto em cêntimos.
     * @return o preço em cêntimos
     */
    public long getPreco() {
        return preco;
    }
    
    
    // ========================================================================
    // SETTER - Alterar o preço
    // ========================================================================
    
    /**
     * Altera o preço do produto.
     * Útil para promoções e atualizações de preço.
     * 
     * @param preco o novo preço em cêntimos (não pode ser negativo)
     * @throws IllegalArgumentException se o preço for negativo
     */
    public void setPreco(long preco) {
        // Validação: preço não pode ser negativo
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        // Atualiza o preço
        this.preco = preco;
    }
    
    
    // ========================================================================
    // MÉTODO AUXILIAR
    // ========================================================================
    
    /**
     * Converte o preço de cêntimos para euros.
     * 
     * @return o preço em euros (com casas decimais)
     */
    public double getPrecoEmEuros() {
        return preco / 100.0;
    }
    
    
    // ========================================================================
    // toString - Para imprimir o objeto
    // ========================================================================
    
    /**
     * Retorna uma representação textual do produto.
     * Útil para debugging.
     * 
     * @return string com informação do produto
     */
    @Override
    public String toString() {
        return String.format("Produto[%s] %s - %s: %.2f€", 
            codigoBarras, marca, modelo, getPrecoEmEuros());
    }
}