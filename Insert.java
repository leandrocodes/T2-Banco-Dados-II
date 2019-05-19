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
    public static int insert_into(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("\nx Inserir Dados");
        System.out.println("    Você deseja inserir dados em qual tabela?");
        System.out.println("    (1) Vendedor");
        System.out.println("    (2) Endereco");
        System.out.println("    (3) Telefone");
        return input.nextInt();
    }
    
    public static Vendedor insert_vendedor(){
        
        Scanner input = new Scanner(System.in);
        Vendedor vend = new Vendedor();
//        input.nextLine();
        System.out.println("Entre com os seguintes dados");
        System.out.print("Nome: ");vend.setNome(input.nextLine());
        System.out.print("Sexo (M ou F): ");vend.setSexo(input.nextLine());
        System.out.print("Email: ");vend.setEmail(input.nextLine());
        System.out.print("CPF: ");vend.setCpf(input.nextLine());
        System.out.print("Janeiro: ");vend.setJaneiro(input.nextLine());
        System.out.print("Fevereiro: ");vend.setFevereiro(input.nextLine());
        System.out.print("Março: ");vend.setMarco(input.nextLine());       
        System.out.println();
        return vend;
    }
    
    public static Telefone insert_telefone(){
        
        Scanner input = new Scanner(System.in);
        Telefone tel = new Telefone();
        System.out.println("Entre com os seguintes dados");            
        
        tel.setIdtelefone(0);
        System.out.print("Tipo ('COM','RES','CEL'): ");tel.setTipo(input.nextLine());
        System.out.print("Número (máx. 10 caracteres): ");tel.setNumero  (input.nextLine());
        System.out.print("ID do Vendedor: ");tel.setId_vendedor(input.nextLine());
        
        return tel;
    }
    
    public static Endereco insert_endereco(){
        
        Scanner input = new Scanner(System.in);
        Endereco end = new Endereco();
        System.out.println("Entre com os seguintes dados");
        
        end.setIdendereco(0);
        System.out.print("Rua: ");end.setRua(input.nextLine());
        System.out.print("Bairro: ");end.setBairro(input.nextLine());
        System.out.print("Cidade: ");end.setCidade(input.nextLine());
        System.out.println("Estado: ");end.setEstado(input.nextLine());
        System.out.print("ID do Vendedor: ");end.setId_vendedor(input.nextLine());
        
        return end;
    }
}
