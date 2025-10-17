package comercio;

import util.Validator;

/**
 * Armazena a informação de um produto, como o código de barras, a marca, o
 * modelo e o preço atual.
 */
public class ProdutoInfo {
	
	// ========== CAMPOS (Variáveis de Instância) ==========
	private String codigoBarras;
	
	private String marca;
	
	private String modelo;
	
	private long preco;

// ========== CONSTRUTOR ==========
    
    /**
     * Cria um novo produto com todos os dados.
     * 
     * @param codigoBarras o código único do produto (não pode ser vazio)
     * @param marca a marca do produto (não pode ser vazio)
     * @param modelo o nome/modelo do produto (não pode ser vazio)
     * @param preco o preço em cêntimos (não pode ser negativo)
     * 
     * @throws IllegalArgumentException se algum parâmetro for inválido
     */
	public ProdutoInfo(String codigoBarras, String marca, String modelo,
			long preco) {
		 // Validator.requireNonBlank verifica:
        // 1. String não é null
        // 2. String não está vazia ("")
        // 3. String não tem só espaços ("   ")
        // Se falhar, lança IllegalArgumentException
		this.codigoBarras = Validator.requireNonBlank(codigoBarras);
		this.marca = Validator.requireNonBlank(marca);
		this.modelo = Validator.requireNonBlank(modelo);
		
		// Validator.requirePositiveOrZero verifica:
        // 1. Número não é negativo
        // Se falhar, lança IllegalArgumentException
		this.preco = Validator.requirePositiveOrZero(preco);
	}
	
	
	 // ========== GETTERS (Métodos para LER os valores) ==========
	
	 /**
     * Retorna o código de barras do produto.
     * 
     * @return o código de barras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }
    
    /**
     * Retorna a marca do produto.
     * 
     * @return a marca
     */
    public String getMarca() {
    	return marca;
    }
    
    
    /**
     * Retorna o modelo/nome do produto.
     * 
     * @return o modelo
     */
    public String getModelo() {
    	return modelo;
    }
    
    
    /**
     * Retorna o preço do produto em cêntimos.
     * 
     * @return o preço em cêntimos
     */
    public long getPreco() {
    	return preco;
    }
    
 // ========== SETTER (Método para ALTERAR um valor) ==========
    
    /**
     * Altera o preço do produto.
     * Útil quando há promoções ou atualizações de preço.
     * 
     * @param preco o novo preço em cêntimos (não pode ser negativo)
     * @throws IllegalArgumentException se o preço for negativo
     */
    public void setPreco(long preco) {
    	this.preco = Validator.requirePositiveOrZero(preco);
    }
    
 // ========== MÉTODOS AUXILIARES ==========
    
    /**
     * Converte o preço de cêntimos para euros.
     * Método auxiliar útil para apresentação.
     * 
     * @return o preço em euros
     */
    public double getPrecoEmEuros() {
    	return preco / 100.0;
    }
    
    
    /**
     * Retorna uma representação em String do produto.
     * Útil para debug e testes.
     * 
     * @return descrição do produto
     */
    @Override
    public String toString() {
        return String.format("ProdutoInfo[código=%s, marca=%s, modelo=%s, preço=%.2f€]",
                           codigoBarras, marca, modelo, getPrecoEmEuros());
    }
    
    
    /**
     * Verifica se dois produtos são iguais (mesmo código de barras).
     * Importante para usar em listas e comparações.
     * 
     * @param obj o objeto a comparar
     * @return true se os produtos têm o mesmo código
     */
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null || getClass() != obj.getClass()) return false;
    	ProdutoInfo other = (ProdutoInfo) obj;
    	return codigoBarras.equals(other.codigoBarras);
    }
    
    
    /**
     * Gera um código hash baseado no código de barras.
     * Necessário quando usamos equals().
     * 
     * @return o hash code
     */
    @Override
    public int hashCode() {
    	return codigoBarras.hashCode();
    }
    
    
    
    
    
    
    
    
    
    
    

}
