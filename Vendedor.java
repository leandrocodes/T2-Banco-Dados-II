package T2_BDII;

public class Vendedor {
    private long idvendedor;
    private String nome;
    private String sexo;
    private String email;
    private String cpf;
    private String janeiro;
    private String fevereiro;
    private String marco;
    
    public Vendedor(){
        
    }

//    public Vendedor(String nome, String sexo, String email, String cpf,
//            String janeiro, String fevereiro, String marco){
//                this(null, nome, sexo, email, cpf, janeiro, fevereiro, marco);
//    }    
    
    public Vendedor(long idvendedor, String nome, String sexo, String email, String cpf,
            String janeiro, String fevereiro, String marco){
        this.idvendedor = idvendedor;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.cpf = cpf;
        this.janeiro = janeiro;
        this.fevereiro = fevereiro;
        this.marco = marco;
    }

    public long getId() {
        return idvendedor;
    }

    public void setId(long idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getJaneiro() {
        return janeiro;
    }

    public void setJaneiro(String janeiro) {
        this.janeiro = janeiro;
    }

    public String getFevereiro() {
        return fevereiro;
    }

    public void setFevereiro(String fevereiro) {
        this.fevereiro = fevereiro;
    }

    public String getMarco() {
        return marco;
    }

    public void setMarco(String marco) {
        this.marco = marco;
    }
}
