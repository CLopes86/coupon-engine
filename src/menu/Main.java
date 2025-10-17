package menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import cliente.Cartao;
import cliente.CupaoProdutos;
import comercio.Inventario;
import comercio.ProdutoInfo;
import comercio.ProdutoVendido;
import comercio.Venda;

public class Main {

    public static void main(String[] args) {
        // criar o inventário e restantes elementos necessário à aplicação
        Inventario inventario = new Inventario();
        lerProdutos(inventario, "dados/produtos.hnt");
        lerCupoes(inventario, "dados/cupoes.hnt");
        lerCartoes(inventario, "dados/cartoes.hnt");

        // para testar alguns aspetos do sistema existem 3 métodos
        // só se deve usar um de cada vez
        testarProdutosMarca(inventario);
        // testarProdutosCompra(inventario);
        // testarErros(inventario);
    }

    /**
     * Este método vai testar os cupões de produtos e de marca.
     * Para isso vai criar uma venda com produtos que fazem parte dos
     * cupões de produtos e outros que não fazem parte.
     * Alguns produtos são da marca com desconto (Albicereal) e são
     * abrangidos por outro cupão (massa), o sistema deve usar o
     * desconto maior
     * 
     * @param inv o inventário a usar no teste
     */
    private static void testarProdutosMarca(Inventario inv) {
        String codigosProdutos[] = { "202-006", // uma massa, que custa 2.31
                "202-008", // uma massa, que custa 2.38
                "202-009", // uma massa, que custa 2.59
                "203-001", // água mineral, que custa 1.56
                "125-011", // chocolate, que custa 2.20
                "125-012", // chocolate, que custa 2.21
                "124-001", // albicereal, arroz, que custa 1.05
                "124-002", // albicereal, arroz, que custa 1.18
                "124-009", // albicereal, massa, que custa 1.73
                "214-016" // Cabeça limpa, sabonete, custa 13.52
        };
        // cupões para chocolates, massas e Albicereal
        String cupoesUsar[] = { "1001", "1003", "1501" };

        processaVenda(inv, codigosProdutos, cupoesUsar, "10101");

    }

    /**
     * 
     * Este método vai testar os cupões de produtos e de compra.
     * Para isso vai criar uma venda com produtos que fazem parte dos
     * cupões de produtos e outros que não fazem parte.
     * Os produtos que não fazem parte devem ser abrangidos pelo de compra
     * nos que são abrangidos, o sistema deve usar o desconto maior
     * 
     * @param inv o inventário a usar no teste
     */
    private static void testarProdutosCompra(Inventario inv) {
        String codigosProdutos[] = { "126-005", // sumo, custa 1.30
                "126-006", // sumo, custa 1.28
                "126-010", // sumo, custa 1.62
                "203-006", // sumo, custa 1.86
                "127-001", // atum, custa 1.08
                "127-011", // polvo, custa 1.93
                "128-008", // uvas, custa 1.51
                "205-010", // manga, custa 2.35
                "124-001", // arroz, custa 1.05
                "124-004", // arroz, custa 1.29

        };
        // cupões para sumos, arroz e compra
        String cupoesUsar[] = { "1002", "1004", "1550" };

        processaVenda(inv, codigosProdutos, cupoesUsar, "30303");
    }

    /**
     * Este método vai testar vários erros. Ir alterando o código de modo a testar
     * os vários erros. Idealmente teriamos um método para cada erro...
     * 
     * @param inv
     */
    private static void testarErros(Inventario inv) {
        // Este método vai testar vários erros. Ir comentando os erros para testar
        // outros
        String codigosProdutos[] = { "126-005", // sumo, custa 1.30
                "127-011", // polvo, custa 1.93
                "128-008", // uvas, custa 1.51
                "205-010", // manga, custa 2.35
                "124-001", // arroz, custa 1.05
                "124-421", // NÃO EXISTE!!
                "222-015", // ovos, 5.45
        };
        // cupões para testes
        String cupoesUsar[] = { "1002", // existe no cartão
                "1014", // não existe, usar também 1003 que existe mas não está no cartão
                "1551" // ainda não está válido
        };

        // testar também com um cartão que não exista
        processaVenda(inv, codigosProdutos, cupoesUsar, "20202");
    }

    /**
     * Realiza uma venda, usando os produtos indicados pelos respetivos códigos, com
     * os cupões indicados ativos e um cartão
     * 
     * @param inv      inventário a usar na venda
     * @param produtos os códigos dos propdutos a serem comprados
     * @param cupoes   código dos cupões a ativar na compra
     * @param cartao   código do cartão a suar na compra
     */
    private static void processaVenda(Inventario inv, String[] produtos, String[] cupoes, String cartao) {
        // TODO criar a venda
        Venda v = null;
        for (String prod : produtos)
            inv.vendeProduto(v, prod);

        // cupões a usar
        ArrayList<CupaoProdutos> cp = new ArrayList<>();
        for (String ccode : cupoes)
            cp.add(inv.getCupao(ccode));

        // usar o cartão que tem os cupões anteriores
        Cartao card = inv.getCartao(cartao);
        long saldoInicial = card.getSaldo();

        System.out.println("----- Situação inicial do cartão -----");
        System.out.println("Cartão: " + card.getNumero() + "  saldo: " + card.getSaldo() / 100.0);
        for (CupaoProdutos c : card.getCupoes()) {
            System.out
                    .println("\tcupao: " + c.getNumero() + " - " + c.getResumo() + (cp.contains(c) ? " - Ativo" : ""));
        }

        card.ativar(cp);

        card.usar(v);
        System.out.println("\n----- descrição dos produtos comprados -----");
        for (ProdutoVendido p : v.getItems()) {
            // TODO colocar as infos corretas
            String prodInfo = String.format("%-15s %-30s %5.2f", "MARCA", "MODELO", 0.0f); // float é preço
            System.out.println(prodInfo);
            // TODO se usou um cupão
            if (Math.abs(2) == -2) {
                // TODO colocar as infos corretas
                System.out.printf("%46s %5.2f\n", "Acumulou em cartão (" + "CUPÃO" + " " + "DESCONTO" + "%)", 0.0f); // float
                                                                                                                     // é
                                                                                                                     // o
                                                                                                                     // acumulado
            }
        }
        long saldoAcumulado = card.getSaldo() - saldoInicial;
        System.out.println("Saldo acumulado: " + saldoAcumulado / 100.0f);

        System.out.println("\n----- Situação final do cartão -----");
        System.out.println("Cartão: " + card.getNumero() + "  saldo: " + card.getSaldo() / 100.0);
        for (CupaoProdutos c : card.getCupoes()) {
            System.out.println("\tcupao: " + c.getNumero() + " - " + c.getResumo());
        }
    }

    /**
     * Cria os produtos e adiciona-os ao inventário
     * 
     * @param inventario onde colocar os produtos
     * @param ficheiro   nome do ficheiro com os produtos
     */
    private static void lerProdutos(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                if (linha.isBlank())
                    continue;
                String info[] = linha.split("\t");
                if (info.length != 4)
                    continue;
                String codigo = info[0];
                String marca = info[1];
                String nome = info[2];
                long preco = Long.parseLong(info[3]);

                // TODO criar a adicionar o produto ao inventário
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos produtos - " + ficheiro + " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos produtos - " + ficheiro + " ! ");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Cria e configura os cupões e adiciona-os ao inventário
     * 
     * @param inventario onde colocar os cupões
     * @param ficheiro   nome do ficheiro com os cupões
     */
    private static void lerCupoes(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                if (linha.isBlank())
                    continue;
                String info[] = linha.split("\t");
                if (info.length < 6)
                    continue;
                String codigo = info[0];
                int diaI = Integer.parseInt(info[1]);
                int diaF = Integer.parseInt(info[2]);
                String tipo = info[3];
                String descricao = info[4];

                float desconto = Integer.parseInt(info[5]) / 100.0f;
                switch (tipo) {
                    case "Produto":
                        ArrayList<ProdutoInfo> prods = new ArrayList<>();
                        for (String code : info[6].split(","))
                            prods.add(inventario.getProduto(code));
                        inventario.addCupao(new CupaoProdutos(codigo, descricao, prods, desconto,
                                LocalDate.now().plusDays(diaI), LocalDate.now().plusDays(diaF)));

                        break;
                    // TODO suportar também os outros tipos de cartões! Neste momento são cupões
                    // de produto sem qualquer produto associado
                    case "Compra":
                        inventario.addCupao(new CupaoProdutos(codigo, descricao, desconto,
                                LocalDate.now().plusDays(diaI), LocalDate.now().plusDays(diaF)));
                        break;
                    case "Marca":
                        inventario.addCupao(new CupaoProdutos(codigo, descricao, desconto,
                                LocalDate.now().plusDays(diaI), LocalDate.now().plusDays(diaF)));
                        break;

                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos cupões - " + ficheiro + " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos cupões - " + ficheiro + "! ");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Cria e configura os cartões e adiciona-os ao inventário
     * 
     * @param inventario onde colocar os cartões
     * @param ficheiro   nome do ficheiro com os cartões
     */
    private static void lerCartoes(Inventario inventario, String ficheiro) {
        try (BufferedReader fin = new BufferedReader(new FileReader(ficheiro))) {
            String linha = null;
            while ((linha = fin.readLine()) != null) {
                if (linha.isBlank())
                    continue;

                String info[] = linha.split("\t");
                if (info.length < 2)
                    continue;

                String codigo = info[0];
                Cartao card = new Cartao(codigo);
                for (String code : info[1].split(","))
                    card.addCupao(inventario.getCupao(code));

                inventario.addCartao(card);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ficheiro dos cartões - " + ficheiro + " - não encontrado! ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erro na leitura do ficheiro dos cartões - " + ficheiro + "! ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
