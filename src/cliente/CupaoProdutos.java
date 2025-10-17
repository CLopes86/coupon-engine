package cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import comercio.ProdutoInfo;
import comercio.ProdutoVendido;
import util.Validator;

/**
 * Classe que representa um cupão emitido pela cadeia de lojas HonESta. Este
 * cupão pode ser associado a um ou mais cartões de fidelização. Cada cupão dá
 * direito a um desconto (em cartão) na compra dos produtos que abrange.
 */
public class CupaoProdutos {
    private String numero;
    private String resumo;
    private float desconto;
    private LocalDate inicio, fim;
    private List<ProdutoInfo> abrangidos = new ArrayList<>();

    public CupaoProdutos(String numero, String resumo, List<ProdutoInfo> abrangidos, float desconto, LocalDate ini,
            LocalDate fim) {
        this.numero = Validator.requireNonBlank(numero);
        this.resumo = Validator.requireNonBlank(resumo);
        if (desconto < 0 || desconto > 1)
            throw new IllegalArgumentException();
        this.desconto = desconto;
        if (ini.isAfter(fim))
            throw new IllegalArgumentException();
        this.inicio = ini;
        this.fim = fim;
        this.abrangidos.addAll(abrangidos);
    }

    public CupaoProdutos(String numero, String resumo, float desconto, LocalDate ini, LocalDate fim) {
        this(numero, resumo, new ArrayList<ProdutoInfo>(), desconto, ini, fim);
    }

    /**
     * Indica se o cupão está válido no dia de hoje, isto é, se o dia de hoje está
     * dentro do prazo de utilização.
     * 
     * @return true se está dentro do prazo de utilização
     */
    public boolean estaValido() {
        return estaValido(LocalDate.now());
    }

    /**
     * Indica se o cupão está válido num dado dia, isto é, se esse dia está dentro
     * do prazo de utilização.
     * 
     * @param data dia em que se pretende verificar se o cupão está válido
     * @return true se a data está dentro do prazo de utilização
     */
    private boolean estaValido(LocalDate data) {
        return data.compareTo(inicio) >= 0 && data.compareTo(fim) <= 0;
    }

    /**
     * Método auxiliar para aplicar o cupão a um produto.
     * 
     * @param c o cartão onde acumular o saldo
     * @param p o produto a ser usado
     */
    public boolean aplicar(Cartao c, ProdutoVendido p) {
        if (!abrange(p))
            return false;
        c.acumularSaldo((long) (p.getPreco() * desconto));
        p.setCupao(this);
        return true;
    }

    public boolean abrange(ProdutoVendido p) {
        return (p.getCupao() == null || p.getCupao().getDesconto() < getDesconto()) && abrangidos.contains(p.getInfo());
    }

    public void addProduto(ProdutoInfo p) {
        abrangidos.add(Objects.requireNonNull(p));
    }

    public void removeProduto(ProdutoInfo p) {
        abrangidos.remove(p);
    }

    public List<ProdutoInfo> getAbrangidos() {
        return Collections.unmodifiableList(abrangidos);
    }

    public String getNumero() {
        return numero;
    }

    public String getResumo() {
        return resumo;
    }

    public float getDesconto() {
        return desconto;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFim() {
        return fim;
    }
}
