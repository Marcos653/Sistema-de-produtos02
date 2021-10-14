/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import uteis.OrdenarDatas;

/**
 *
 * @author Marcos
 */
public class Menu {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Itens> itens = new ArrayList<>();
        List<Vendas> venda = new ArrayList<>();       
        Scanner ler = new Scanner(System.in);    
        
        public void incluirProdutos(){
            try{
                System.out.print("Aperte ENTER"); 
                ler.nextLine();
                System.out.println("\nQual nome do produto:");
                String prodNome = ler.nextLine();
                System.out.println("Qual o código que deseja por no produto:");
                int prodCodigo = ler.nextInt();
                System.out.println("Qual valor do produto:");
                int prodValor = ler.nextInt();
                System.out.println("Qual a quantidade que você quer por no estoque:");
                int prodEstoque = ler.nextInt();
                
                itens.add(new Itens(prodNome, prodCodigo, prodValor, prodEstoque));                 
            }catch(RuntimeException e){
                System.out.println("Você inseriu algo errado! Tente novamente");
            }
        }
        
        public void consultarProdutos(){
                System.out.println("----Consultar produto----");
                System.out.print("Aperte ENTER"); 
                ler.nextLine();

                System.out.println("Por favor, informe o nome do produto que deseja procurar");
                String nomeP = ler.nextLine();

                for(Itens produtos : itens){
                    if(produtos.getNome().equals(nomeP)){
                        System.out.println(produtos.toString());
                    }

                }                 
            
        }
        
        public void listagemProdutos(){
            System.out.println("----Listagem de produtos----");
            System.out.print("Aperte ENTER"); 
            ler.nextLine();
            for (int i=0; i< itens.size(); i++){
                System.out.println(itens.get(i));
            }

            double valorMax = 0, valorMin = 999999999, valorTotal = 0;

            for (Itens produtos : itens) {
                valorTotal = valorTotal + produtos.getValor();

                if(produtos.getValor() > valorMax){
                    valorMax = produtos.getValor();
                }
                if(produtos.getValor() < valorMin){
                    valorMin = produtos.getValor();
                }
            }
            System.out.println("Valor medio = " + valorTotal / itens.size());
            System.out.println("Valor maximo = " + valorMax);
            System.out.println("Valor minimo = "+ valorMin);                
        }
        
        public void vendasProduto(){
                System.out.println("********** VENDAS **********\n");
                
                System.out.print("Aperte ENTER"); 
                ler.nextLine();   
                
                if(itens.isEmpty()){
                    System.out.println("Não há produtos cadastrados.");
                }else{
                    try{
                    System.out.println("Qual o codigo do produto que deseja comprar:");
                    int cod = ler.nextInt();
                    boolean acheiProdutos = false;
                    for (Itens p : itens) {
                        if(p.getCodigo() == cod){
                            ler.nextLine();
                            acheiProdutos = true;
                            System.out.println(" Insira a data nesse formato -> dd/mm/aaaa");
                            String data = ler.nextLine();
                           
                            System.out.println("Qual a quantidade:");
                            int quantidade = ler.nextInt();
                            ler.nextLine();
                            if(p.getQuantEstoque() < quantidade){
                                System.out.println("Não há quantidade suficiente no estoque. \n");
                                break;
                            }else {
                                p.removerQuant(quantidade);
                                venda.add(new Vendas(quantidade,p,LocalDate.parse(data,df)));
                                System.out.println("Venda realizada com sucesso!!");
                            }
                          
                        }
                        
                    }
                    if(acheiProdutos == false){
                        System.out.println("Produto não cadastrado.");
                    }
                    
                    }catch(RuntimeException e){
                        System.out.println("Você inseriu algo errado! Tente novamente");
                    }  
                    
                    
                }
        }
        
        
        public void relatorioPeriodo(){
            double valorMedio = 0;
            double valorMax = 0;
            int quant = 0;
            
            System.out.println("***** VENDAS POR PERÍODO - DETALHADO *****");
            System.out.print("Aperte ENTER"); 
            ler.nextLine();            
                if(venda.isEmpty()){
                    System.out.println("Não foi efetuada nenhuma venda por enquanto.");
                }else{
                    System.out.println("Data de inicio (dd/MM/yyyy):");
                    String dataInicial = ler.nextLine();
                    System.out.println("Data final (dd/MM/yyyy)");
                    String dataFinal = ler.nextLine();

                    List<Vendas> filtrarVendas = venda.stream().filter(p ->  {
                    Vendas vendaa = (Vendas)p;
                    return (vendaa.getDataDeVenda().isEqual(LocalDate.parse(dataInicial, df)) || vendaa.getDataDeVenda().isEqual(LocalDate.parse(dataFinal, df))) ||
                    (vendaa.getDataDeVenda().isBefore(LocalDate.parse(dataFinal, df)) && (vendaa.getDataDeVenda().isAfter(LocalDate.parse(dataInicial, df))));
                    }).collect(Collectors.toList());
                                    
                    filtrarVendas.sort(new OrdenarDatas());
                    for (Vendas f : filtrarVendas) {
                        System.out.printf("     DATA || NOME || QUANTIDADE || VALOR UNITÁRIO || VALOR TOTAL\n ");
                        System.out.println(f.getDataDeVenda()+",   "+ f.getItens().getNome() + ",       "+ f.getQuantVendida()+",            "+ f.getItens().getValor() +",            "+ f.calcularTotal());
                        valorMax += f.calcularTotal();
                        quant++;
                                    }
                                 }
                
                System.out.println("--------------------------------------------");
                valorMedio = valorMax / quant;
                System.out.printf("Valor medio do periodo: R$ " + valorMedio);
        }
}
