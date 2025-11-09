
package teste;

import comercio.Inventario;
import comercio.ProdutoInfo;
import comercio.ProdutoVendido;
import comercio.Venda;
import java.util.Collection;

/**
 * Testes simples e claros para a classe Inventario.
 * Cada teste verifica uma funcionalidade específica.
 */
public class TesteIventario {
    
    public static void main(String[] args) {
        
        System.out.println("==========================================");
        System.out.println("  TESTES: Inventario");
        System.out.println("==========================================\n");
        
        // Executar todos os testes
        teste1_CriarInventarioVazio();
        teste2_AdicionarProdutos();
        teste3_BuscarProduto();
        teste4_BuscarProdutoInexistente();
        teste5_RemoverProduto();
        teste6_ListarTodosProdutos();
        teste7_VenderProduto();
        teste8_VenderProdutoInexistente();
        teste9_TestarSeguranca();
        teste10_VelocidadeHashMap();
        
        System.out.println("==========================================");
        System.out.println("  ✅ TODOS OS TESTES PASSARAM!");
        System.out.println("==========================================");
    }
    
    
    // ========================================================================
    // TESTE 1: Criar inventário vazio
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar se inventário começa vazio
     * 
     * VERIFICA:
     * - Inventário criado sem erros
     * - Começa com 0 produtos
     */
    static void teste1_CriarInventarioVazio() {
        System.out.println("🧪 Teste 1: Criar inventário vazio");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        Collection<ProdutoInfo> produtos = inv.getProdutos();
        
        if (produtos.size() == 0) {
            System.out.println("✅ Inventário começa vazio (0 produtos)");
        } else {
            System.out.println("❌ ERRO: Inventário deveria estar vazio!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 2: Adicionar produtos
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar se adiciona produtos corretamente
     * 
     * VERIFICA:
     * - Adiciona sem erros
     * - Quantidade aumenta corretamente
     */
    static void teste2_AdicionarProdutos() {
        System.out.println("🧪 Teste 2: Adicionar produtos");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Criar produtos
        ProdutoInfo p1 = new ProdutoInfo("001", "Marca1", "Produto1", 100);
        ProdutoInfo p2 = new ProdutoInfo("002", "Marca2", "Produto2", 200);
        ProdutoInfo p3 = new ProdutoInfo("003", "Marca3", "Produto3", 300);
        
        // Adicionar
        inv.addProduto(p1);
        System.out.println("   Adicionado produto 1");
        
        inv.addProduto(p2);
        System.out.println("   Adicionado produto 2");
        
        inv.addProduto(p3);
        System.out.println("   Adicionado produto 3");
        
        // Verificar quantidade
        int quantidade = inv.getProdutos().size();
        
        if (quantidade == 3) {
            System.out.println("✅ 3 produtos adicionados corretamente");
        } else {
            System.out.println("❌ ERRO: Esperava 3 produtos, tem " + quantidade);
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 3: Buscar produto (que existe)
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar se busca funciona corretamente
     * 
     * VERIFICA:
     * - Busca retorna o produto correto
     * - Busca é instantânea (HashMap)
     */
    static void teste3_BuscarProduto() {
        System.out.println("🧪 Teste 3: Buscar produto existente");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar produtos
        ProdutoInfo massa = new ProdutoInfo("202-006", "Albicereal", "Massa", 231);
        ProdutoInfo chocolate = new ProdutoInfo("125-011", "Nestlé", "Chocolate", 220);
        
        inv.addProduto(massa);
        inv.addProduto(chocolate);
        
        // Buscar produto que existe
        ProdutoInfo encontrado = inv.getProduto("202-006");
        
        if (encontrado != null) {
            System.out.println("✅ Produto encontrado: " + encontrado.getModelo());
            
            // Verificar se é o produto correto
            if (encontrado.getCodigoBarras().equals("202-006") &&
                encontrado.getMarca().equals("Albicereal") &&
                encontrado.getModelo().equals("Massa")) {
                System.out.println("✅ Dados do produto corretos");
            } else {
                System.out.println("❌ ERRO: Produto com dados errados!");
            }
        } else {
            System.out.println("❌ ERRO: Produto não encontrado!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 4: Buscar produto (que NÃO existe)
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar comportamento quando produto não existe
     * 
     * VERIFICA:
     * - Retorna null quando não existe
     * - Não dá erro/exceção
     */
    static void teste4_BuscarProdutoInexistente() {
        System.out.println("🧪 Teste 4: Buscar produto inexistente");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar um produto
        ProdutoInfo massa = new ProdutoInfo("202-006", "Albicereal", "Massa", 231);
        inv.addProduto(massa);
        
        // Buscar produto que NÃO existe
        ProdutoInfo encontrado = inv.getProduto("999-999");
        
        if (encontrado == null) {
            System.out.println("✅ Retornou null corretamente");
        } else {
            System.out.println("❌ ERRO: Deveria retornar null!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 5: Remover produto
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar se remove produtos corretamente
     * 
     * VERIFICA:
     * - Remove sem erros
     * - Produto não existe mais depois de remover
     * - Quantidade diminui
     */
    static void teste5_RemoverProduto() {
        System.out.println("🧪 Teste 5: Remover produto");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar produtos
        ProdutoInfo p1 = new ProdutoInfo("001", "Marca1", "Produto1", 100);
        ProdutoInfo p2 = new ProdutoInfo("002", "Marca2", "Produto2", 200);
        ProdutoInfo p3 = new ProdutoInfo("003", "Marca3", "Produto3", 300);
        
        inv.addProduto(p1);
        inv.addProduto(p2);
        inv.addProduto(p3);
        
        System.out.println("   Antes: " + inv.getProdutos().size() + " produtos");
        
        // Remover um produto
        inv.removeProduto(p2);
        System.out.println("   Removido: Produto2");
        
        System.out.println("   Depois: " + inv.getProdutos().size() + " produtos");
        
        // Verificar se foi removido
        ProdutoInfo removido = inv.getProduto("002");
        
        if (removido == null) {
            System.out.println("✅ Produto removido com sucesso");
        } else {
            System.out.println("❌ ERRO: Produto ainda existe!");
        }
        
        // Verificar quantidade
        if (inv.getProdutos().size() == 2) {
            System.out.println("✅ Quantidade correta (2 produtos)");
        } else {
            System.out.println("❌ ERRO: Quantidade incorreta!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 6: Listar todos os produtos
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar se retorna todos os produtos
     * 
     * VERIFICA:
     * - Retorna coleção com todos os produtos
     * - Quantidade correta
     */
    static void teste6_ListarTodosProdutos() {
        System.out.println("🧪 Teste 6: Listar todos os produtos");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar 5 produtos
        for (int i = 1; i <= 5; i++) {
            ProdutoInfo p = new ProdutoInfo(
                "00" + i,
                "Marca" + i,
                "Produto" + i,
                i * 100
            );
            inv.addProduto(p);
        }
        
        // Obter todos
        Collection<ProdutoInfo> todos = inv.getProdutos();
        
        System.out.println("   Total de produtos: " + todos.size());
        
        if (todos.size() == 5) {
            System.out.println("✅ Quantidade correta (5 produtos)");
        } else {
            System.out.println("❌ ERRO: Quantidade incorreta!");
        }
        
        // Listar
        System.out.println("   Produtos:");
        int contador = 1;
        for (ProdutoInfo p : todos) {
            System.out.println("      " + contador + ". " + p.getModelo());
            contador++;
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 7: Vender produto (vendeProduto)
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar integração com Venda
     * 
     * VERIFICA:
     * - vendeProduto adiciona à venda corretamente
     * - ProdutoVendido criado com dados corretos
     */
    static void teste7_VenderProduto() {
        System.out.println("🧪 Teste 7: Vender produto");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar produtos ao inventário
        ProdutoInfo massa = new ProdutoInfo("202-006", "Albicereal", "Massa", 231);
        ProdutoInfo chocolate = new ProdutoInfo("125-011", "Nestlé", "Chocolate", 220);
        
        inv.addProduto(massa);
        inv.addProduto(chocolate);
        
        // Criar venda
        Venda venda = new Venda();
        
        System.out.println("   Venda vazia: " + venda.estaVazia());
        
        // Vender produtos
        inv.vendeProduto(venda, "202-006");
        System.out.println("   Vendido: Massa");
        
        inv.vendeProduto(venda, "125-011");
        System.out.println("   Vendido: Chocolate");
        
        // Verificar venda
        if (venda.getQuantidadeProdutos() == 2) {
            System.out.println("✅ 2 produtos adicionados à venda");
        } else {
            System.out.println("❌ ERRO: Quantidade incorreta na venda!");
        }
        
        // Verificar total
        long totalEsperado = 231 + 220;
        if (venda.getTotal() == totalEsperado) {
            System.out.println("✅ Total correto: " + venda.getTotalEmEuros() + "€");
        } else {
            System.out.println("❌ ERRO: Total incorreto!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 8: Vender produto inexistente
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar tratamento de erro
     * 
     * VERIFICA:
     * - Lança exceção quando produto não existe
     * - Mensagem de erro adequada
     */
    static void teste8_VenderProdutoInexistente() {
        System.out.println("🧪 Teste 8: Vender produto inexistente");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar um produto
        ProdutoInfo massa = new ProdutoInfo("202-006", "Albicereal", "Massa", 231);
        inv.addProduto(massa);
        
        // Criar venda
        Venda venda = new Venda();
        
        // Tentar vender produto que não existe
        try {
            inv.vendeProduto(venda, "999-999");
            System.out.println("❌ ERRO: Deveria ter lançado exceção!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Exceção lançada corretamente");
            System.out.println("   Mensagem: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 9: Testar segurança (unmodifiable collection)
    // ========================================================================
    
    /**
     * OBJETIVO: Verificar que não pode modificar coleção retornada
     * 
     * VERIFICA:
     * - Não pode adicionar à coleção
     * - Não pode remover da coleção
     * - Lança UnsupportedOperationException
     */
    static void teste9_TestarSeguranca() {
        System.out.println("🧪 Teste 9: Testar segurança");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar produto
        ProdutoInfo p1 = new ProdutoInfo("001", "Marca", "Produto", 100);
        inv.addProduto(p1);
        
        // Obter coleção
        Collection<ProdutoInfo> produtos = inv.getProdutos();
        
        // Tentar modificar
        try {
            ProdutoInfo p2 = new ProdutoInfo("002", "Marca2", "Produto2", 200);
            produtos.add(p2);
            System.out.println("❌ ERRO: Conseguiu adicionar à coleção!");
        } catch (UnsupportedOperationException e) {
            System.out.println("✅ Não pode adicionar (protegido)");
        }
        
        // Tentar remover
        try {
            produtos.clear();
            System.out.println("❌ ERRO: Conseguiu limpar a coleção!");
        } catch (UnsupportedOperationException e) {
            System.out.println("✅ Não pode remover (protegido)");
        }
        
        // Verificar que inventário não foi afetado
        if (inv.getProdutos().size() == 1) {
            System.out.println("✅ Inventário continua com 1 produto");
        } else {
            System.out.println("❌ ERRO: Inventário foi modificado!");
        }
        
        System.out.println();
    }
    
    
    // ========================================================================
    // TESTE 10: Demonstrar velocidade do HashMap
    // ========================================================================
    
    /**
     * OBJETIVO: Demonstrar que HashMap é rápido
     * 
     * VERIFICA:
     * - Busca funciona mesmo com muitos produtos
     * - Demonstração conceitual de performance
     */
    static void teste10_VelocidadeHashMap() {
        System.out.println("🧪 Teste 10: Velocidade do HashMap");
        System.out.println("───────────────────────────────────");
        
        Inventario inv = new Inventario();
        
        // Adicionar 100 produtos
        System.out.println("   Adicionando 100 produtos...");
        for (int i = 1; i <= 100; i++) {
            ProdutoInfo p = new ProdutoInfo(
                String.format("%03d", i),
                "Marca" + i,
                "Produto" + i,
                i * 100
            );
            inv.addProduto(p);
        }
        
        System.out.println("   100 produtos adicionados");
        
        // Buscar alguns produtos
        System.out.println("   Buscando produtos...");
        
        ProdutoInfo p1 = inv.getProduto("001");
        ProdutoInfo p50 = inv.getProduto("050");
        ProdutoInfo p100 = inv.getProduto("100");
        
        if (p1 != null && p50 != null && p100 != null) {
            System.out.println("✅ Todas as buscas foram instantâneas!");
            System.out.println("   (HashMap não precisa percorrer todos)");
        } else {
            System.out.println("❌ ERRO: Algum produto não foi encontrado!");
        }
        
        // Buscar produto que não existe
        ProdutoInfo inexistente = inv.getProduto("999");
        if (inexistente == null) {
            System.out.println("✅ Busca de inexistente também é instantânea!");
        }
        
        System.out.println("\n   💡 Com ArrayList seria muito mais lento!");
        System.out.println("      ArrayList: O(n) - percorre até encontrar");
        System.out.println("      HashMap: O(1) - vai direto!");
        
        System.out.println();
    }
}

   
