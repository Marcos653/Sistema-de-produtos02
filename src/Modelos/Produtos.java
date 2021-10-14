/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Marcos
 */
public abstract class  Produtos implements Comparable<Produtos> {
    private String nome;
    private int codigo;
    private double valor;
    private int quantEstoque;
    
    
     public void removerQuant( int quant){
         quantEstoque -= quant;
     }
       
    public Produtos(String nome, int codigo, double valor, int quantEstoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.quantEstoque = quantEstoque;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getCodigo() {
        return codigo;
    }


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public int getQuantEstoque() {
        return quantEstoque;
    }


    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    @Override
    public String toString() {
        return "Produtos- codigo - " + codigo + ", nome - " + nome + ", quantEstoque - " + quantEstoque + ", valor - " + valor;
    }

    
}
