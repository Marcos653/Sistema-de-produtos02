/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Marcos
 */
public class Vendas{
    private int quantVendida;
    private Itens itens;
    private LocalDate dataDeVenda;
    
    public double calcularTotal(){
        return quantVendida * itens.getValor();
    }    

    public Vendas(int quantVendida, Itens itens, LocalDate dataDeVenda) {
        this.quantVendida = quantVendida;
        this.itens = itens;
        this.dataDeVenda = dataDeVenda;
    }
   
    

    public int getQuantVendida() {
        return quantVendida;
    }

    public void setQuantVendida(int quantVendida) {
        this.quantVendida = quantVendida;
    }

    public Itens getItens() {
        return itens;
    }

    public void setItens(Itens itens) {
        this.itens = itens;
    }

    public LocalDate getDataDeVenda() {
        return dataDeVenda;
    }

    public void setDataDeVenda(LocalDate dataDeVenda) {
        this.dataDeVenda = dataDeVenda;
    }

    @Override
    public String toString() {
        return "Vendas{" + "quantVendida=" + quantVendida + ", itens=" + itens + ", dataDeVenda=" + dataDeVenda + '}';
    }
    
    
    
    
}
