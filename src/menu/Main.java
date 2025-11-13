package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import cliente.Cartao;
import cliente.Cupao;
import cliente.CupaoCompra;
import cliente.CupaoMarca;
import cliente.CupaoProdutos;
import comercio.Inventario;
import comercio.ProdutoInfo;
import comercio.ProdutoVendido;
import comercio.Venda;

/**
 * Classe principal do sistema de cupões da HonESTa.
 * 
 * RESPONSABILIDADES:
 * - Carregar dados dos ficheiros (produtos, cupões, cartões)
 * - Testar funcionalidades do sistema
 * - Processar vendas com aplicação de cupões
 * 
 * ESTRUTURA:
 * 1. main() - Carrega dados e executa testes
 * 2. testar*() - Métodos de teste para diferentes cenários
 * 3. processaVenda() - Simula uma venda completa
 * 4. ler*() - Métodos para ler ficheiros de dados
 * 
 * FICHEIROS DE DADOS:
 * - dados/produtos.hnt - Produtos disponíveis
 * - dados/cupoes.hnt - Cupões do sistema
 * - dados/cartoes.hnt - Cartões de clientes
 */
public class Main {

    // ========================================================================
    // MÉTODO PRINCIPAL
    // ========================================================================
    
    /**
     * Ponto de entrada da aplicação.
     * 
     * FLUXO:
     * 1. Cria inventário vazio
     * 2. Carrega produtos do ficheiro
     * 3. Carrega cupões do ficheiro
     * 4. Carrega cartões do ficheiro
     * 5. Executa testes
     * 
     * @param args argumentos da linha de comando (não usados)
     */
    public static void main(String[] args) {
        // Criar o inventário central
        Inventario inventario = new Inventario();
        
        // Carregar dados dos ficheiros
        lerProdutos(inventario, "dados/produtos.hnt");
        lerCupoes(inventario, "dados/cupoes.hnt");
        lerCartoes(inventario, "dados/cartoes.hnt");

        // Executar testes
        // NOTA: Comentar/descomentar para testar cenários diferentes
        testarProdutosMarca(inventario);
         testarProdutosCompra(inventario);
         testarErros(inventario);
    }

    // ========================================================================
    // MÉTODOS DE TESTE
    // ========================================================================
    
    /**
     * Testa cupões de produtos e de marca.
     * 
     * CENÁRIO:
     * - Produtos com cupão de produtos (massas, chocolates)
     * - Produtos com cupão de marca (Albicereal)
     * - Produtos sem cupão (água, sabonete)
     * - Produtos com múltiplos cupões aplicáveis (massa Albicereal)
     * 
     * COMPORTAMENTO ESPERADO:
     * - Sistema escolhe cupão com maior desconto
     * - Cupões usados são removidos
     * - Saldo é acumulado corretamente
     * 
     * @param inv o inventário a usar no teste
     */
    private static void testarProdutosMarca(Inventario inv) {
        String codigosProdutos[] = { 
            "202-006", // uma massa, que custa 2.31
            "202-008", // uma massa, que custa 2.38
            "202-009", // uma massa, que custa 2.59
            "203-001", // água mineral, que custa 1.56
            "125-011", // chocolate, que custa 2.20
            "125-012", // chocolate, que custa 2.21
            "124-001", // albicereal, arroz, que custa 1.05
            "124-002", // albicereal, arroz, que custa 1.18
            "124-009", // albicereal, massa, que custa 1.73
            "214-016"  // Cabeça limpa, sabonete, custa 13.52
        };
        
        // Cupões: chocolates (15%), massas (15%), Albicereal (10%)
        String cupoesUsar[] = { "1001", "1003", "1501" };

        processaVenda(inv, codigosProdutos, cupoesUsar, "10101");
    }

    /**
     * Testa cupões de produtos e de compra.
     * 
     * CENÁRIO:
     * - Produtos com cupão específico (sumos, arroz)
     * - Produtos sem cupão específico (atum, polvo, uvas, manga)
     * - Cupão de compra (10%) para cobrir produtos sem cupão
     * 
     * COMPORTAMENTO ESPERADO:
     * - Produtos com cupão específico usam esse cupão
     * - Produtos sem cupão específico usam cupão de compra
     * - Sistema sempre escolhe maior desconto
     * 
     * @param inv o inventário a usar no teste
     */
    private static void testarProdutosCompra(Inventario inv) {
        String codigosProdutos[] = { 
            "126-005", // sumo, custa 1.30
            "126-006", // sumo, custa 1.28
            "126-010", // sumo, custa 1.62
            "203-006", // sumo, custa 1.86
            "127-001", // atum, custa 1.08
            "127-011", // polvo, custa 1.93
            "128-008", // uvas, custa 1.51
            "205-010", // manga, custa 2.35
            "124-001", // arroz, custa 1.05
            "124-004"  // arroz, custa 1.29
        };
        
        // Cupões: sumos (15%), arroz (15%), compra (10%)
        String cupoesUsar[] = { "1002", "1004", "1550" };

        processaVenda(inv, codigosProdutos, cupoesUsar, "30303");
    }

    /**
     * Testa tratamento de erros.
     * 
     * CENÁRIO:
     * - Produto que não existe (código inválido)
     * - Cupão que não existe
     * - Cupão que ainda não está válido
     * - Cupão que não está no cartão
     * - Cartão que não existe
     * 
     * COMPORTAMENTO ESPERADO:
     * - Sistema lança exceções apropriadas
     * - Erros são reportados claramente
     * 
     * NOTA: Comentar/descomentar linhas para testar erros individuais
     * 
     * @param inv o inventário a usar no teste
     */
    private static void testarErros(Inventario inv) {
        String codigosProdutos[] = { 
            "126-005", // sumo, custa 1.30
            "127-011", // polvo, custa 1.93
            "128-008", // uvas, custa 1.51
            "205-010", // manga, custa 2.35
            "124-001", // arroz, custa 1.05
            // "124-421", // NÃO EXISTE!! (descomentar para testar)
            "222-015"  // ovos, 5.45
        };
        
        // Cupões para testes de erro
        String cupoesUsar[] = { 
            "1002", // existe no cartão
            // "1014", // não existe (descomentar para testar)
            // "1551"  // ainda não está válido (descomentar para testar)
        };

        // Testar também com cartão inexistente
        processaVenda(inv, codigosProdutos, cupoesUsar, "20202");
    }

    // ========================================================================
    // PROCESSAMENTO DE VENDA
    // ========================================================================
    
    /**
     * Realiza uma venda completa com aplicação de cupões.
     * 
     * FLUXO:
     * 1. Cria venda e adiciona produtos
     * 2. Busca cupões a ativar
     * 3. Mostra situação inicial do cartão
     * 4. Ativa cupões no cartão
     * 5. Usa cartão (aplica cupões)
     * 6. Mostra produtos comprados e descontos
     * 7. Mostra situação final do cartão
     * 
     * LÓGICA DE APLICAÇÃO:
     * - Cupões são ordenados por desconto (maior primeiro)
     * - Cada produto pode ter no máximo 1 cupão
     * - Cupões usados são removidos do cartão
     * - Cupões não usados permanecem no cartão
     * 
     * @param inv inventário com produtos, cupões e cartões
     * @param produtos códigos dos produtos a comprar
     * @param cupoes códigos dos cupões a ativar
     * @param cartao código do cartão a usar
     */
    private static void processaVenda(Inventario inv, String[] produtos, 
                                     String[] cupoes, String cartao) {
        // PASSO 1: Criar venda e adicionar produtos
        Venda v = new Venda();
        for (String prod : produtos)
            inv.vendeProduto(v, prod);

        // PASSO 2: Buscar cupões a ativar
        ArrayList<Cupao> cp = new ArrayList<>();
        for (String ccode : cupoes)
            cp.add(inv.getCupao(ccode));

        // PASSO 3: Buscar cartão e guardar saldo inicial
        Cartao card = inv.getCartao(cartao);
        long saldoInicial = card.getSaldo();

        // ====================================================================
        // MOSTRAR SITUAÇÃO INICIAL
        // ====================================================================
        
        System.out.println("----- Situação inicial do cartão -----");
        System.out.println("Cartão: " + card.getNumero() + 
                          "  saldo: " + card.getSaldo() / 100.0);
        
        for (Cupao c : card.getCupoes()) {
            System.out.println("\tcupao: " + c.getNumero() + " - " + 
                             c.getResumo() + 
                             (cp.contains(c) ? " - Ativo" : ""));
        }

        // ====================================================================
        // PROCESSAR VENDA
        // ====================================================================
        
        card.ativar(cp);  // Ativa cupões selecionados
        card.usar(v);     // Aplica cupões e acumula saldo
        
        // ====================================================================
        // MOSTRAR PRODUTOS E DESCONTOS
        // ====================================================================
        
        System.out.println("\n----- descrição dos produtos comprados -----");
        for (ProdutoVendido p : v.getItems()) {
            // Mostrar produto: Marca | Nome | Preço
            String prodInfo = String.format("%-15s %-30s %5.2f", 
                p.getInfo().getMarca(),
                p.getInfo().getModelo(),
                p.getPreco() / 100.0f);
            System.out.println(prodInfo);
            
            // Se usou cupão, mostrar desconto acumulado
            if (p.getCupao() != null) {
                Cupao cupaoUsado = p.getCupao();
                float descontoValor = p.getPreco() * cupaoUsado.getDesconto();
                System.out.printf("%46s %5.2f\n", 
                    "Acumulou em cartão (" + cupaoUsado.getNumero() + " " + 
                    (cupaoUsado.getDesconto() * 100) + "%)", 
                    descontoValor / 100.0f);
            }
        }
        
        // ====================================================================
        // MOSTRAR RESUMO
        // ====================================================================
        
        long saldoAcumulado = card.getSaldo() - saldoInicial;
        System.out.println("Saldo acumulado: " + saldoAcumulado / 100.0f);

        System.out.println("\n----- Situação final do cartão -----");
        System.out.println("Cartão: " + card.getNumero() + 
                          "  saldo: " + card.getSaldo() / 100.0);
        for (Cupao c : card.getCupoes()) {
            System.out.println("\tcupao: " + c.getNumero() + " - " + c.getResumo());
        }
    }

    // ========================================================================
    // LEITURA DE FICHEIROS
    // ========================================================================
    
    /**
     * Cria produtos a partir de um ficheiro e adiciona ao inventário.
     * 
     * FORMATO DO FICHEIRO (separado por tabs):
     * codigo \t marca \t nome \t preco
     * 
     * EXEMPLO:
     * 202-006  Arquipélago  Massa esparguete 500g  231
     * 
     * TRATAMENTO DE ERROS:
     * - Linhas vazias são ignoradas
     * - Linhas com formato errado são ignoradas
     * - Ficheiro não encontrado → termina programa
     * - Erro de leitura → termina programa
     * 
     * @param inventario onde adicionar os produtos
     * @param ficheiro caminho do ficheiro
     */
    private static void lerProdutos(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                // Ignorar linhas vazias
                if (linha.isBlank())
                    continue;
                
                // Dividir linha por tabs
                String info[] = linha.split("\t");
                if (info.length != 4)
                    continue;
                
                // Extrair campos
                String codigo = info[0];
                String marca = info[1];
                String nome = info[2];
                long preco = Long.parseLong(info[3]);

                // Criar e adicionar produto
                ProdutoInfo produto = new ProdutoInfo(codigo, marca, nome, preco);
                inventario.addProduto(produto);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos produtos - " + ficheiro + 
                              " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos produtos - " + 
                              ficheiro + " ! ");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Cria cupões a partir de um ficheiro e adiciona ao inventário.
     * 
     * FORMATO DO FICHEIRO (separado por tabs):
     * numero \t diaI \t diaF \t tipo \t descricao \t percentagem \t [dados]
     * 
     * TIPOS DE CUPÃO:
     * - "Produto" → dados = lista de códigos separados por vírgula
     * - "Marca" → dados = nome da marca
     * - "Compra" → sem dados extra
     * 
     * EXEMPLO:
     * 1001  0  6  Produto  Desconto em chocolates  15  125-008,125-009
     * 1501  0  6  Marca    Desconto Albicereal     10  Albicereal
     * 1550  0  6  Compra   Desconto geral          10
     * 
     * DATAS:
     * - diaI e diaF são relativos a hoje (LocalDate.now())
     * - 0 = hoje, 7 = daqui a 7 dias, -7 = há 7 dias atrás
     * 
     * @param inventario onde adicionar os cupões
     * @param ficheiro caminho do ficheiro
     */
    private static void lerCupoes(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                // Ignorar linhas vazias
                if (linha.isBlank())
                    continue;
                
                // Dividir linha por tabs
                String info[] = linha.split("\t");
                if (info.length < 6)
                    continue;
                
                // Campos comuns a todos os cupões
                String codigo = info[0];
                int diaI = Integer.parseInt(info[1]);
                int diaF = Integer.parseInt(info[2]);
                String tipo = info[3];
                String descricao = info[4];
                float desconto = Integer.parseInt(info[5]) / 100.0f;
                
                // Criar cupão conforme o tipo
                switch (tipo) {
                    case "Produto":
                        // Cupão de produtos específicos
                        ArrayList<ProdutoInfo> prods = new ArrayList<>();
                        for (String code : info[6].split(","))
                            prods.add(inventario.getProduto(code));
                        
                        inventario.addCupao(new CupaoProdutos(
                            codigo, descricao, prods, desconto,
                            LocalDate.now().plusDays(diaI), 
                            LocalDate.now().plusDays(diaF)
                        ));
                        break;
                        
                    case "Compra":
                        // Cupão de compra (abrange tudo)
                        inventario.addCupao(new CupaoCompra(
                            codigo, descricao, desconto,
                            LocalDate.now().plusDays(diaI), 
                            LocalDate.now().plusDays(diaF)
                        ));
                        break;
                        
                    case "Marca":
                        // Cupão de marca
                        String marca = info[6];
                        inventario.addCupao(new CupaoMarca(
                            codigo, descricao, desconto,
                            LocalDate.now().plusDays(diaI), 
                            LocalDate.now().plusDays(diaF),
                            marca
                        ));
                        break;

                    default:
                        System.err.println("Tipo de cupão desconhecido: " + tipo);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos cupões - " + ficheiro + 
                              " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos cupões - " + 
                              ficheiro + "! ");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Cria cartões a partir de um ficheiro e adiciona ao inventário.
     * 
     * FORMATO DO FICHEIRO (separado por tabs):
     * numero \t cupoes
     * 
     * EXEMPLO:
     * 10101  1001,1003,1501,1502
     * 
     * FORMATO DE CUPÕES:
     * - Lista de códigos separados por vírgula
     * - Sem espaços
     * 
     * @param inventario onde adicionar os cartões
     * @param ficheiro caminho do ficheiro
     */
    private static void lerCartoes(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                // Ignorar linhas vazias
                if (linha.isBlank())
                    continue;

                // Dividir linha por tabs
                String info[] = linha.split("\t");
                if (info.length < 2)
                    continue;

                // Criar cartão
                String codigo = info[0];
                Cartao card = new Cartao(codigo);
                
                // Adicionar cupões ao cartão
                for (String code : info[1].split(","))
                    card.addCupao(inventario.getCupao(code));

                // Adicionar cartão ao inventário
                inventario.addCartao(card);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos cartões - " + ficheiro + 
                              " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos cartões - " + 
                              ficheiro + "! ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}