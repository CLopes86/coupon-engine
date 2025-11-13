package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comercio.ProdutoVendido;
import comercio.Venda;
import util.Validator;
import cliente.Cupao;

/**
 * Classe que representa um cartão de fidelização na cadeia de lojas HonESta.
 */
public class Cartao {
    private String numero;
    private long saldo;
    private List<Cupao> cupoes = new ArrayList<>();
    private List<Cupao> cupoesAtivos = new ArrayList<>();
    private boolean ativo = false;

    public Cartao(String numero, long saldo) {
        this.numero = Validator.requireNonBlank(numero);
        this.saldo = Validator.requirePositiveOrZero(saldo);
    }

    public Cartao(String numero) {
        this(numero, 0);
    }

    public String getNumero() {
        return numero;
    }

    public long getSaldo() {
        return saldo;
    }

    public void addCupao(Cupao c) {
        cupoes.add(Objects.requireNonNull(c));
    }

    public void removeCupao(Cupao c) {
        cupoes.remove(c);
    }

    public List<Cupao> getCupoes() {
        return Collections.unmodifiableList(cupoes);
    }

    public boolean estaAtivo() {
        return ativo;
    }

    /**
     * Ativa os cupões selecionados. Não aceita cupões que não estejam na lista de
     * cupões do cliente
     * 
     * @param ativos lista de cupões para ativar
     */
    public void ativar(List<Cupao> ativos) {
        ativo = true;
        if (!cupoes.containsAll(ativos))
            throw new IllegalArgumentException("Há cupões que não pertencem ao cartão");
        cupoesAtivos.clear();
        cupoesAtivos.addAll(ativos);
    }

    /**
     * Usar o cartão numa venda, se este estiver ativo. Se tiver cupões ativos estes
     * devem ser aplicados também. Os cupões usados na venda serão removidos. Os
     * cupões ativos mas não usados na venda, deixam de estar ativos, mas permanecem
     * associados ao cartão. No final do uso o cartão é automaticamente desativado.
     * 
     * @param v a venda onde usar o cartão
     * @throws IllegalStateException se o cartão não estiver ativo
     */
   public void usar(Venda v) {
    if (!estaAtivo())
        throw new IllegalStateException();

    // Ordenar cupões por desconto (MAIOR primeiro)
    Collections.sort(cupoesAtivos, (c1, c2) -> 
        Float.compare(c2.getDesconto(), c1.getDesconto()));

    // Ver quais produtos estão abrangidos por algum cupão
    for (ProdutoVendido pv : v.getItems()) {
        
        // Se produto já tem cupão, pular
        if (pv.getCupao() != null) {
            continue;
        }
        
        // Tentar aplicar cupões (do maior para o menor)
        for (Cupao c : cupoesAtivos) {
            //  ↑
            // MUDANÇA: Cupao em vez de CupaoProdutos
            
            // Verificar se cupão está válido E abrange o produto
            if (c.estaValido() && c.abrange(pv)) {
                
                // Calcular e acumular desconto
                long descontoValor = (long) (pv.getPreco() * c.getDesconto());
                acumularSaldo(descontoValor);
                
                // Marcar produto com cupão
                pv.setCupao(c);
                
                // Remover cupão (foi usado)
                cupoes.remove(c);
                
                // Parar (só um cupão por produto)
                break;
            }
        }
    }
    
    atualizarCupoes();
    ativo = false;
}

    /**
     * Retorna uma lista com os cupões disponíveis. Os cupões disponíveis são
     * aqueles que estão válidos no dia atual.
     * 
     * @return uma lista com os cupões disponíveis
     */
    public List<Cupao> getCupoesDisponiveis() {
        ArrayList<Cupao> disponiveis = new ArrayList<>();
        for (Cupao c : cupoes)
            if (c.estaValido())
                disponiveis.add(c);
        return disponiveis;
    }

    /**
     * Retorna uma lista com os cupões que estarão disponíveis no futuro, isto é,
     * cuja início é após o dia de hoje.
     * 
     * @return uma lista com os cupões que estarão disponíveis no futuro
     */
    public List<Cupao> getCupoesFuturos() {
        ArrayList<Cupao> res = new ArrayList<>();
        for (Cupao c : cupoes)
            if (c.getInicio().isAfter(LocalDate.now()))
                res.add(c);
        return res;
    }

    /**
     * Atualiza os cupões, removendo os que já passaram de validade
     */
    public void atualizarCupoes() {
        for (int i = cupoes.size() - 1; i >= 0; i--) {
            Cupao c = cupoes.get(i);
            if (c.getFim().isBefore(LocalDate.now()))
                cupoes.remove(i);
        }
        cupoesAtivos.clear();
    }

    /**
     * Usar o saldo do cartão. Deve garantir que o gasto não é maior que o saldo.
     * 
     * @param gasto o que retirar do saldo.
     */
    public void reduzirSaldo(long gasto) {
        Validator.requirePositiveOrZero(gasto);
        if (gasto > saldo)
            throw new IllegalArgumentException();
        saldo -= gasto;
    }

    /**
     * Acumular saldo no cartão
     * 
     * @param valor valor a acumular no saldo
     */
    public void acumularSaldo(long valor) {
        Validator.requirePositiveOrZero(valor);
        saldo += valor;
    }

}
