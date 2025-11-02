package teste;

import comercio.ProdutoInfo;
import comercio.ProdutoVendido;

public class ProdutoVendidoTest {
    
    public static void main(String[] args) {
        System.out.println("========== TESTES ProdutoVendido ==========\n");
        
        testarGetPreco();
        
        System.out.println("\n========== TODOS OS TESTES OK! ==========");
    }

    public static void testarGetPreco() {
    System.out.println("Teste: getPreco()");

    try {
        ProdutoInfo produto = new ProdutoInfo("123", "Marca", "Modelo", 231);
        ProdutoVendido pv = new ProdutoVendido(produto, 231);

        float preco = pv.getPreco();
        System.out.println("Preço retornado: " + preco);
        System.out.println();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
}

    
   
}