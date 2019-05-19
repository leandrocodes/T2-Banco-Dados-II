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
public class Endereco {
    private long idendereco;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String id_vendedor;
    
    public Endereco(){
        
    }

    public Endereco(long idendereco, String rua, String bairro, String cidade, String estado, String id_vendedor){
        this.idendereco = idendereco;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.id_vendedor = id_vendedor;
    }
    
    public long getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(long idendereco) {
        this.idendereco = idendereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
}
