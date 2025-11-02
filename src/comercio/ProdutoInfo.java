
package comercio;


/**
 * Representa um produto da loja.
 * Armazena informação básica: código, marca, modelo e preço.
 */
public class ProdutoInfo {
     // ========== CAMPOS (Variáveis de Instância) ==========
     private String codigoBarras;
     private String marca;
     private String modelo;
     private long preco;

      // ========== CONSTRUTOR ==========
      /**
     * Cria um novo produto com todas as informações.
     * 
     * @param codigoBarras o código de barras único
     * @param marca a marca do produto
     * @param modelo o nome/modelo do produto
     * @param preco o preço em cêntimos
     * @throws IllegalAccessException 
     */
    public ProdutoInfo(String codigoBarras, String marca, String modelo, long preco) throws IllegalAccessException{
        if (codigoBarras == null || codigoBarras.isBlank()) {
            throw new IllegalArgumentException("Código não pode ser vazio!");
        }

        if (marca == null || marca.isBlank()){
            throw new IllegalArgumentException("Marca não pode ser vazia!");
        }

         if (modelo == null || modelo.isBlank()){
            throw new IllegalArgumentException("Modelo não pode ser vazia!");
         }

         if (preco < 0){
            throw new IllegalArgumentException("Preço não pode ser negativo");
         }

        this.codigoBarras = codigoBarras;
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
        
    }

     // ========== GETTERS ==========
     /**
     * Retorna o código de barras do produto.
     * 
     * @return o código de barras
     */
    public String getCodigoBarras(){
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

    // ========== SETTER ==========
    
    /**
     * Altera o preço do produto.
     * Útil quando há promoções ou atualizações de preço.
     * 
     * @param preco o novo preço em cêntimos
     */
    public void setPreco(long preco) {
        if (preco < 0) {
            throw new  IllegalArgumentException("Preço não pode ser negativo!");
            
        }
    }


    /**
     * Converte o preço de cêntimos para euros.
     * 
     * @return o preço em euros
     */

    public double getPrecoEmEuros(){
        return preco / 100.0;
    }   
}