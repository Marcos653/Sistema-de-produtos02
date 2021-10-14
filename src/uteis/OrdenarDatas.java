/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import Modelos.Vendas;
import java.util.Comparator;

/**
 *
 * @author Marcos
 */
public class OrdenarDatas implements Comparator<Vendas>  {

    @Override
    public int compare(Vendas venda1, Vendas venda2) {
        if(venda1.getDataDeVenda() == null || venda2.getDataDeVenda() == null){
            return 0;
        }
        return venda1.getDataDeVenda().compareTo(venda2.getDataDeVenda());
    }
    
}
