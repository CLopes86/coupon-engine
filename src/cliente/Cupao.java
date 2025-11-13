package cliente;

import java.time.LocalDate;

import comercio.ProdutoVendido;
import util.Validator;

/**
 * Classe ABSTRATA base para todos os tipos de cupões do sistema HonESTa.
 * 
 * ============================================================================
 * CONCEITOS IMPORTANTES
 * ============================================================================
 * 
 * CLASSE ABSTRATA:
 * - NÃO pode ser instanciada diretamente (new Cupao() dá erro!)
 * - Define comportamento COMUM a todos os cupões
 * - Força subclasses a implementar método abstrato abrange()
 * - Serve como "contrato" que todas as subclasses devem seguir
 * 
 * HERANÇA:
 * Esta classe é PAI de:
 * - CupaoProdutos → desconto em produtos específicos da lista
 * - CupaoMarca → desconto em todos os produtos de uma marca
 * - CupaoCompra → desconto em toda a compra (qualquer produto)
 * 
 * POLIMORFISMO:
 * Podemos ter uma lista de Cupao que contém qualquer tipo:
 *   List<Cupao> cupoes = new ArrayList<>();
 *   cupoes.add(new CupaoProdutos(...));  ✅
 *   cupoes.add(new CupaoMarca(...));     ✅
 *   cupoes.add(new CupaoCompra(...));    ✅
 * 
 * Depois, ao chamar métodos:
 *   cupoes.get(0).abrange(produto);
 * 
 * O Java executa automaticamente a versão correta de abrange()
 * dependendo do tipo REAL do objeto em runtime!
 * 
 * ============================================================================
 * ESTRUTURA
 * ============================================================================
 * 
 * CAMPOS COMUNS (protected):
 * - numero: identificação única do cupão
 * - resumo: descrição curta
 * - desconto: percentagem (0.0 a 1.0)
 * - inicio, fim: período de validade
 * 
 * MÉTODOS CONCRETOS (com implementação):
 * - estaValido(): verifica validade
 * - getters: acesso aos campos
 * 
 * MÉTODO ABSTRATO (sem implementação):
 * - abrange(): cada subclasse implementa de forma diferente
 * 
 * ============================================================================
 * EXEMPLO DE USO
 * ============================================================================
 * 
 * // ❌ ERRO - não pode instanciar classe abstrata
 * Cupao c = new Cupao(...);  
 * 
 * // ✅ CORRETO - instanciar subclasses
 * Cupao c1 = new CupaoProdutos(...);
 * Cupao c2 = new CupaoMarca(...);
 * Cupao c3 = new CupaoCompra(...);
 * 
 * // Todos têm os mesmos métodos herdados
 * c1.estaValido();  ✅
 * c2.getDesconto(); ✅
 * c3.abrange(produto); ✅
 */
public abstract class Cupao {
    
    // ========================================================================
    // CAMPOS COMUNS - Todos os cupões têm estes campos
    // ========================================================================
    
    /**
     * Número único de identificação do cupão.
     * Exemplo: "1001", "1501", "1550"
     */
    private String numero;
    
    /**
     * Descrição resumida do cupão.
     * Exemplo: "15% em chocolates", "10% Albicereal"
     */
    private String resumo;
    
    /**
     * Percentagem de desconto como decimal.
     * 
     * IMPORTANTE:
     * - Armazenado como 0.0 a 1.0 (não como 0 a 100)
     * - 0.15 = 15%
     * - 0.10 = 10%
     * - 1.00 = 100%
     * 
     * Para mostrar: desconto * 100
     * Para calcular: preco * desconto
     */
    private float desconto;
    
    /**
     * Data de início da validade do cupão.
     * Cupão só pode ser usado se data atual >= inicio
     */
    private LocalDate inicio;
    
    /**
     * Data de fim da validade do cupão.
     * Cupão só pode ser usado se data atual <= fim
     */
    private LocalDate fim;

    // ========================================================================
    // CONSTRUTOR PROTEGIDO
    // ========================================================================
    
    /**
     * Construtor da classe base.
     * 
     * PROTECTED:
     * - Só pode ser chamado por SUBCLASSES (CupaoProdutos, CupaoMarca, etc.)
     * - O mundo exterior NÃO pode criar Cupao diretamente
     * - Garante que só subclasses concretas são instanciadas
     * 
     * VALIDAÇÕES:
     * - Número não pode ser vazio (usa Validator)
     * - Resumo não pode ser vazio (usa Validator)
     * - Desconto deve estar entre 0 e 1 (0% a 100%)
     * - Data início deve ser antes ou igual a data fim
     * 
     * SUPER:
     * Subclasses chamam este construtor usando super():
     *   public CupaoProdutos(...) {
     *       super(numero, resumo, desconto, inicio, fim);
     *       // código específico da subclasse
     *   }
     * 
     * @param numero número único do cupão (não vazio)
     * @param resumo descrição do cupão (não vazia)
     * @param desconto percentagem de desconto (0.0 a 1.0)
     * @param inicio data de início da validade
     * @param fim data de fim da validade
     * @throws IllegalArgumentException se algum parâmetro for inválido
     */
    protected Cupao(String numero, String resumo, float desconto,
                   LocalDate inicio, LocalDate fim) {
        
        // Validação 1: Número não pode ser vazio
        this.numero = Validator.requireNonBlank(numero);
        
        // Validação 2: Resumo não pode ser vazio
        this.resumo = Validator.requireNonBlank(resumo);

        // Validação 3: Desconto entre 0 e 1
        if (desconto < 0 || desconto > 1) {
            throw new IllegalArgumentException(
                "Desconto deve estar entre 0 e 1 (0% a 100%)");
        }
        this.desconto = desconto;

        // Validação 4: Data início antes ou igual a data fim
        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException(
                "Data de início deve ser antes ou igual a data de fim");
        }
        this.inicio = inicio;
        this.fim = fim;
    }
     
    // ========================================================================
    // GETTERS - Consultar informação (métodos concretos)
    // ========================================================================
    
    /**
     * Retorna o número do cupão.
     * @return o número único do cupão
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Retorna o resumo/descrição do cupão.
     * @return a descrição do cupão
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * Retorna o desconto como decimal (0.0 a 1.0).
     * 
     * Para mostrar percentagem: getDesconto() * 100
     * Para calcular valor: preco * getDesconto()
     * 
     * @return o desconto (0.15 = 15%, 0.10 = 10%)
     */
    public float getDesconto() {
        return desconto;
    }

    /**
     * Retorna a data de início da validade.
     * @return a data de início
     */
    public LocalDate getInicio() {
        return inicio;
    }

    /**
     * Retorna a data de fim da validade.
     * @return a data de fim
     */
    public LocalDate getFim() {
        return fim;
    }

    // ========================================================================
    // MÉTODOS CONCRETOS - Com implementação (todas subclasses herdam)
    // ========================================================================
    
    /**
     * Verifica se o cupão está válido HOJE.
     * 
     * Um cupão está válido se a data de hoje estiver
     * dentro do período de validade (início <= hoje <= fim).
     * 
     * MÉTODO CONCRETO:
     * - Tem implementação completa
     * - Funciona igual para TODOS os tipos de cupão
     * - Todas as subclasses herdam este comportamento
     * - Não precisa ser sobrescrito (override)
     * 
     * IMPLEMENTAÇÃO:
     * Chama estaValido(LocalDate.now()) para verificar hoje
     * 
     * @return true se o cupão está válido hoje, false caso contrário
     */
    public boolean estaValido() {
        return estaValido(LocalDate.now());
    }

    /**
     * Verifica se o cupão está válido numa data específica.
     * 
     * LÓGICA:
     * A data deve estar no intervalo [inicio, fim] (inclusive)
     * - data >= inicio E data <= fim
     * 
     * IMPLEMENTAÇÃO:
     * - !data.isBefore(inicio) é o mesmo que data >= inicio
     * - !data.isAfter(fim) é o mesmo que data <= fim
     * 
     * Por que usar ! (negação)?
     * - LocalDate não tem >= ou <=
     * - Tem isBefore() e isAfter()
     * - Então usamos negação: "não é antes" = "é depois ou igual"
     * 
     * @param data a data a verificar
     * @return true se a data está dentro do período de validade
     */
    public boolean estaValido(LocalDate data) {
        // data >= inicio  E  data <= fim
        return !data.isBefore(inicio) && !data.isAfter(fim);
    }

    // ========================================================================
    // MÉTODO ABSTRATO - Sem implementação (subclasses DEVEM implementar)
    // ========================================================================
    
    /**
     * Verifica se este cupão abrange um dado produto.
     * 
     * MÉTODO ABSTRATO:
     * - NÃO tem implementação aqui (sem corpo { })
     * - Cada subclasse DEVE implementar este método
     * - Cada tipo de cupão verifica de forma diferente
     * - Usar @Override na subclasse
     * 
     * IMPLEMENTAÇÕES NAS SUBCLASSES:
     * 
     * CupaoProdutos:
     *   - Verifica se produto está na lista de produtos abrangidos
     *   - return abrangidos.contains(p.getInfo());
     * 
     * CupaoMarca:
     *   - Verifica se a marca do produto coincide com a marca do cupão
     *   - return marca.equalsIgnoreCase(p.getInfo().getMarca());
     * 
     * CupaoCompra:
     *   - Sempre retorna true (abrange todos os produtos)
     *   - return true;
     * 
     * POR QUE É ABSTRATO?
     * Não há uma forma única de verificar que funcione para
     * TODOS os tipos de cupões. Cada tipo tem sua própria lógica.
     * 
     * POLIMORFISMO EM AÇÃO:
     * Quando chamamos cupao.abrange(produto), o Java
     * automaticamente executa a versão correta dependendo
     * do tipo REAL do objeto em runtime!
     * 
     * @param p o produto a verificar
     * @return true se o cupão se aplica a este produto
     */
    public abstract boolean abrange(ProdutoVendido p);

   
    }
