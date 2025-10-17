package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comercio.ProdutoVendido;
import comercio.Venda;
import util.Validator;

/**
 * Classe que representa um cartão de fidelização na cadeia de lojas HonESta.
 */
public class Cartao {
    private String numero;
    private long saldo;
    private List<CupaoProdutos> cupoes = new ArrayList<>();
    private List<CupaoProdutos> cupoesAtivos = new ArrayList<>();
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

    public void addCupao(CupaoProdutos c) {
        cupoes.add(Objects.requireNonNull(c));
    }

    public void removeCupao(CupaoProdutos c) {
        cupoes.remove(c);
    }

    public List<CupaoProdutos> getCupoes() {
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
    public void ativar(List<CupaoProdutos> ativos) {
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

        // ordenar os cupões pela ordem do desconto (assim não temos de verificar
        // se o produto já tem um desconto maior ou não)
        Collections.sort(cupoesAtivos, (c1, c2) -> c1.getDesconto() > c2.getDesconto() ? 1 : -1);

        // ver quais os produtos que estão abrangidos por algum cupão
        for (ProdutoVendido pv : v.getItems()) {
            for (CupaoProdutos c : cupoesAtivos) {
                // se o cupão se aplicar, deve ser removido da lista de cupões
                // não adianta confirmar se já foi eliminado pois o remove já faz isso
                if (c.aplicar(this, pv))
                    cupoes.remove(c);
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
    public List<CupaoProdutos> getCupoesDisponiveis() {
        ArrayList<CupaoProdutos> disponiveis = new ArrayList<>();
        for (CupaoProdutos c : cupoes)
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
    public List<CupaoProdutos> getCupoesFuturos() {
        ArrayList<CupaoProdutos> res = new ArrayList<>();
        for (CupaoProdutos c : cupoes)
            if (c.getInicio().isAfter(LocalDate.now()))
                res.add(c);
        return res;
    }

    /**
     * Atualiza os cupões, removendo os que já passaram de validade
     */
    public void atualizarCupoes() {
        for (int i = cupoes.size() - 1; i >= 0; i--) {
            CupaoProdutos c = cupoes.get(i);
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
