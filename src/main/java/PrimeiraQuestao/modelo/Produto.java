package PrimeiraQuestao.modelo;

import java.util.Date;

public class Produto {

    int id;
    String nome;
    String descricao;
    double desconto;
    double preco;
    Date dataInicio;

    public Produto() {

    }

    public Produto(String nome, String descricao, double desconto, double preco, Date dataInicio) {
        this.nome = nome;
        this.descricao = descricao;
        this.desconto = desconto;
        this.preco = preco;
        this.dataInicio = dataInicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                "nome='" + nome + '\'' +
                "descricao='" + descricao + '\'' +
                "desconto=" + desconto +
                "preco=" + preco +
                "dataInicio=" + dataInicio;
    }
}