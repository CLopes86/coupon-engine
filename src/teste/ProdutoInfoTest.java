package teste;

import comercio.ProdutoInfo;

/**
 * Classe de teste para ProdutoInfo.
 * Testa todos os métodos e casos especiais.
 */
public class ProdutoInfoTest {
    
    /**
     * Contador de testes executados e passados
     */
    private static int testesExecutados = 0;
    private static int testesPassados = 0;
    
    
    /**
     * Método principal que executa todos os testes
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  TESTES DA CLASSE ProdutoInfo");
        System.out.println("========================================\n");
        
        // Executar todos os testes
        testarConstrutor();
        testarGetters();
        testarSetPreco();
        testarPrecoEmEuros();
        testarValidacoes();
        testarEquals();
        
        // Mostrar resultados finais
        System.out.println("\n========================================");
        System.out.println("  RESULTADOS FINAIS");
        System.out.println("========================================");
        System.out.println("Testes executados: " + testesExecutados);
        System.out.println("Testes passados: " + testesPassados);
        System.out.println("Testes falhados: " + (testesExecutados - testesPassados));
        
        if (testesPassados == testesExecutados) {
            System.out.println("\n✅ TODOS OS TESTES PASSARAM! 🎉");
        } else {
            System.out.println("\n❌ ALGUNS TESTES FALHARAM!");
        }
    }
    
    
    /**
     * Testa se o construtor cria o produto corretamente
     */
    private static void testarConstrutor() {
        System.out.println("--- Teste: Construtor ---");
        
        try {
            ProdutoInfo p = new ProdutoInfo("202-006", "Continente", "Massa Esparguete", 231);
            
            // Verificar se os valores foram guardados corretamente
            boolean passou = true;
            
            if (!p.getCodigoBarras().equals("202-006")) {
                System.out.println("❌ Código de barras incorreto!");
                passou = false;
            }
            
            if (!p.getMarca().equals("Continente")) {
                System.out.println("❌ Marca incorreta!");
                passou = false;
            }
            
            if (!p.getModelo().equals("Massa Esparguete")) {
                System.out.println("❌ Modelo incorreto!");
                passou = false;
            }
            
            if (p.getPreco() != 231) {
                System.out.println("❌ Preço incorreto!");
                passou = false;
            }
            
            testesExecutados++;
            if (passou) {
                testesPassados++;
                System.out.println("✅ Construtor funciona corretamente!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Exceção inesperada: " + e.getMessage());
            testesExecutados++;
        }
        
        System.out.println();
    }
    
    
    /**
     * Testa se os getters retornam os valores corretos
     */
    private static void testarGetters() {
        System.out.println("--- Teste: Getters ---");
        
        try {
            ProdutoInfo p = new ProdutoInfo("123-ABC", "TestMarca", "TestModelo", 500);
            
            boolean passou = true;
            
            // Testar cada getter
            if (!p.getCodigoBarras().equals("123-ABC")) {
                System.out.println("❌ getCodigoBarras() falhou!");
                passou = false;
            }
            
            if (!p.getMarca().equals("TestMarca")) {
                System.out.println("❌ getMarca() falhou!");
                passou = false;
            }
            
            if (!p.getModelo().equals("TestModelo")) {
                System.out.println("❌ getModelo() falhou!");
                passou = false;
            }
            
            if (p.getPreco() != 500) {
                System.out.println("❌ getPreco() falhou!");
                passou = false;
            }
            
            testesExecutados++;
            if (passou) {
                testesPassados++;
                System.out.println("✅ Todos os getters funcionam!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Exceção inesperada: " + e.getMessage());
            testesExecutados++;
        }
        
        System.out.println();
    }
    
    
    /**
     * Testa se o setPreco altera o preço corretamente
     */
    private static void testarSetPreco() {
        System.out.println("--- Teste: setPreco ---");
        
        try {
            ProdutoInfo p = new ProdutoInfo("123", "Marca", "Modelo", 100);
            
            // Alterar o preço
            p.setPreco(250);
            
            if (p.getPreco() == 250) {
                testesExecutados++;
                testesPassados++;
                System.out.println("✅ setPreco() funciona corretamente!");
            } else {
                testesExecutados++;
                System.out.println("❌ setPreco() não alterou o preço!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Exceção inesperada: " + e.getMessage());
            testesExecutados++;
        }
        
        System.out.println();
    }
    
    
    /**
     * Testa a conversão de cêntimos para euros
     */
    private static void testarPrecoEmEuros() {
        System.out.println("--- Teste: getPrecoEmEuros ---");
        
        try {
            ProdutoInfo p = new ProdutoInfo("123", "Marca", "Modelo", 231);
            
            double precoEuros = p.getPrecoEmEuros();
            
            // Verificar se 231 cêntimos = 2.31 euros
            if (Math.abs(precoEuros - 2.31) < 0.001) {
                testesExecutados++;
                testesPassados++;
                System.out.println("✅ Conversão para euros funciona! (231 cêntimos = 2.31€)");
            } else {
                testesExecutados++;
                System.out.println("❌ Conversão incorreta! Esperado: 2.31€, Obtido: " + precoEuros + "€");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Exceção inesperada: " + e.getMessage());
            testesExecutados++;
        }
        
        System.out.println();
    }
    
    
    /**
     * Testa se as validações funcionam (valores inválidos devem dar erro)
     */
    private static void testarValidacoes() {
        System.out.println("--- Teste: Validações ---");
        int subtestes = 0;
        int subpassados = 0;
        
        // Teste 1: Código vazio
        try {
            new ProdutoInfo("", "Marca", "Modelo", 100);
            System.out.println("❌ Deveria rejeitar código vazio!");
            subtestes++;
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Rejeitou código vazio corretamente");
            subtestes++;
            subpassados++;
        }
        
        // Teste 2: Marca vazia
        try {
            new ProdutoInfo("123", "", "Modelo", 100);
            System.out.println("❌ Deveria rejeitar marca vazia!");
            subtestes++;
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Rejeitou marca vazia corretamente");
            subtestes++;
            subpassados++;
        }
        
        // Teste 3: Modelo vazio
        try {
            new ProdutoInfo("123", "Marca", "", 100);
            System.out.println("❌ Deveria rejeitar modelo vazio!");
            subtestes++;
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Rejeitou modelo vazio corretamente");
            subtestes++;
            subpassados++;
        }
        
        // Teste 4: Preço negativo
        try {
            new ProdutoInfo("123", "Marca", "Modelo", -50);
            System.out.println("❌ Deveria rejeitar preço negativo!");
            subtestes++;
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Rejeitou preço negativo corretamente");
            subtestes++;
            subpassados++;
        }
        
        // Teste 5: setPreco com valor negativo
        try {
            ProdutoInfo p = new ProdutoInfo("123", "Marca", "Modelo", 100);
            p.setPreco(-10);
            System.out.println("❌ setPreco deveria rejeitar valor negativo!");
            subtestes++;
        } catch (IllegalArgumentException e) {
            System.out.println("✅ setPreco rejeitou valor negativo corretamente");
            subtestes++;
            subpassados++;
        }
        
        testesExecutados++;
        if (subtestes == subpassados) {
            testesPassados++;
            System.out.println("✅ Todas as validações funcionam! (" + subpassados + "/" + subtestes + ")");
        } else {
            System.out.println("❌ Algumas validações falharam! (" + subpassados + "/" + subtestes + ")");
        }
        
        System.out.println();
    }
    
    
    /**
     * Testa se o método equals funciona corretamente
     */
    private static void testarEquals() {
        System.out.println("--- Teste: equals ---");
        
        try {
            ProdutoInfo p1 = new ProdutoInfo("123", "Marca", "Modelo", 100);
            ProdutoInfo p2 = new ProdutoInfo("123", "OutraMarca", "OutroModelo", 200);
            ProdutoInfo p3 = new ProdutoInfo("456", "Marca", "Modelo", 100);
            
            boolean passou = true;
            
            // Produtos com mesmo código devem ser iguais
            if (!p1.equals(p2)) {
                System.out.println("❌ Produtos com mesmo código devem ser iguais!");
                passou = false;
            }
            
            // Produtos com códigos diferentes devem ser diferentes
            if (p1.equals(p3)) {
                System.out.println("❌ Produtos com códigos diferentes devem ser diferentes!");
                passou = false;
            }
            
            // Produto deve ser igual a si mesmo
            if (!p1.equals(p1)) {
                System.out.println("❌ Produto deve ser igual a si mesmo!");
                passou = false;
            }
            
            testesExecutados++;
            if (passou) {
                testesPassados++;
                System.out.println("✅ equals() funciona corretamente!");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Exceção inesperada: " + e.getMessage());
            testesExecutados++;
        }
        
        System.out.println();
    }
}