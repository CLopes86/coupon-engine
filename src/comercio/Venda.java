package comercio;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa uma venda (compra) com múltiplos produtos.
 * 
 * É como um carrinho de compras que:
 * - Guarda uma lista de produtos vendidos
 * - Calcula o total da venda
 * - Permite adicionar e remover produtos
 * 
 * USA ARRAYLIST:
 * A lista de produtos é dinâmica, pode crescer à medida
 * que produtos são adicionados.
 */
public class Venda {

   // ========================================================================
    // CAMPOS
    // ========================================================================
    
    /**
     * Lista de produtos vendidos nesta venda.
     * Usa ArrayList para poder crescer dinamicamente.
     * 
     * Por que ArrayList e não array normal?
     * - Array: tamanho fixo (ProdutoVendido[] produtos = new ProdutoVendido[10])
     * - ArrayList: tamanho dinâmico (cresce automaticamente)
     */
    private ArrayList<ProdutoVendido> produtos;

     // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    /**
     * Cria uma nova venda vazia.
     * A lista de produtos começa vazia e vai crescendo
     * à medida que adicionamos produtos.
     */
    public Venda() {
        this.produtos = new ArrayList<>();
    }

      // ========================================================================
    // MÉTODOS PRINCIPAIS
    // ========================================================================
    
    /**
     * Adiciona um produto à venda.
     * O produto é adicionado no final da lista.
     * 
     * @param produto o produto vendido a adicionar
     * @throws IllegalArgumentException se produto for null
     */
    public void adicionarProduto(ProdutoVendido produto){
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode set null!");
        }
        produtos.add(produto);
    }


     /**
     * Retorna a lista de produtos vendidos.
     * 
     * SEGURANÇA IMPORTANTE:
     * Retorna uma lista NÃO MODIFICÁVEL para proteger
     * a lista interna. Ninguém pode adicionar/remover
     * produtos acedendo diretamente à lista retornada.
     * 
     * Se tentar modificar a lista retornada, vai dar erro:
     *   venda.getItems().add(produto);  // ❌ ERRO!
     * 
     * Para adicionar, use adicionarProduto():
     *   venda.adicionarProduto(produto);  // ✅ CORRETO
     * 
     * @return lista não modificável dos produtos
     */
    public List<ProdutoVendido> getItems(){
        return Collections.unmodifiableList(produtos);

    }

     /**
     * Calcula e retorna o valor total da venda.
     * Soma os preços de todos os produtos.
     * 
     * ALGORITMO:
     * 1. Começa com total = 0
     * 2. Para cada produto na lista:
     *    - Soma o preço do produto ao total
     * 3. Retorna o total
     * 
     * @return o total da venda em cêntimos
     */
    public long  getTotal(){
        long total = 0;
         // Percorrer todos os produtos
        // For-each: "para cada produto nos produtos"
        for (ProdutoVendido produto : produtos) {
            total += produto.getPreco();
        }

        return total;

    }

    // ========================================================================
    // MÉTODOS AUXILIARES
    // ========================================================================
    
    /**
     * Retorna a quantidade de produtos na venda.
     * 
     * @return número de produtos
     */
    public int getQuantidadeProdutos() {
        return produtos.size();
    }

     /**
     * Retorna o total em euros (convertido de cêntimos).
     * 
     * @return o total em euros
     */
    public double getTotalEmEuros(){
        return getTotal() / 100.0;
    }

    /**
     * Verifica se a venda está vazia (sem produtos).
     * 
     * @return true se não tem produtos, false caso contrário
     */
    public boolean estaVazia(){
        return produtos.isEmpty();
    }

    /**
     * Remove todos os produtos da venda.
     * Útil para cancelar uma venda ou reiniciar o carrinho.
     */
    public void limpar (){
        produtos.clear();
    }


     // ========================================================================
    // toString
    // ========================================================================
    
    /**
     * Retorna uma representação textual da venda.
     * Mostra a quantidade de produtos e o total.
     * 
     * @return string com informação da venda
     */
    @Override
    public String toString() {
        return String.format("Venda: %d produtos, Total: %.2f€", 
            getQuantidadeProdutos(), 
            getTotalEmEuros());
    }
}
