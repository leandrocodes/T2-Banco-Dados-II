/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T2_BDII;

import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Insert {
    public static void insert_into(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Você deseja inserir dados em qual tabela?");
        System.out.println("(1) Vendedor");
        System.out.println("(2) Telefone");
        System.out.println("(3) Endereco");
        switch(input.nextInt()){
            case 1:
                Vendedor vend = new Vendedor();
                System.out.println("Entre com os seguintes dados");
                        
                vend.setId(null);
                System.out.print("Nome: ");vend.setNome(input.nextLine());
                System.out.print("Sexo: ");vend.setSexo(input.nextLine());
                System.out.print("Email: ");vend.setEmail(input.nextLine());
                System.out.print("CPF: ");vend.setCpf(input.nextLine());
                System.out.print("Janeiro: ");vend.setJaneiro(input.nextLine());
                System.out.print("Fevereiro: ");vend.setFevereiro(input.nextLine());
                System.out.print("Março: ");vend.setMarco(input.nextLine());
                //database.insert(con, vend);                
                break;
                
            case 2:
                Telefone tel = new Telefone();
                System.out.println("Entre com os seguintes dados");
                        
                tel.setIdtelefone(null);
                System.out.print("Tipo: ");tel.setTipo(input.nextLine());
                System.out.print("Número: ");tel.setNumero  (input.nextLine());
                System.out.print("ID do Vendedor: ");tel.setId_vendedor(input.nextLine());               
                break;
                
            case 3:
                Endereco end = new Endereco();
                System.out.println("Entre com os seguintes dados");
                        
                end.setIdendereco(null);
                System.out.print("Rua: ");end.setRua(input.nextLine());
                System.out.print("Bairro: ");end.setBairro(input.nextLine());
                System.out.print("Cidade: ");end.setCidade(input.nextLine());
                System.out.println("Estado: ");end.setEstado(input.nextLine());
                System.out.print("ID do Vendedor: ");end.setId_vendedor(input.nextLine());                     
                break;
        }
    }
}
