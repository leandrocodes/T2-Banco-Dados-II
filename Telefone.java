/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T2_BDII;

/**
 *
 * @author daniel
 */
public class Telefone {
    public String idtelefone;
    public String tipo;
    public String numero;
    public String id_vendedor;    
    
    public Telefone(){
        
    }
    
    public Telefone(String idtelefone, String tipo, String numero, String id_vendedor){
        this.idtelefone = idtelefone;
        this.tipo = tipo;
        this.numero = numero;
        this.id_vendedor = id_vendedor;
    }

    public String getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(String idtelefone) {
        this.idtelefone = idtelefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
}
