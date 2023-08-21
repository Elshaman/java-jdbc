package org.cbo.jdbc.modelo;

public class Competencia {

    private Long id;
    private String nombre;
    private String codigo;
    private Programa programa;

    public Competencia() {
    }

    



    @Override
    public String toString() {
        return "Competencia [nombre=" + nombre + ", codigo=" + codigo + ", programa=" + programa.getNombre() + "]";
    }





    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    

    public Programa getPrograma() {
        return programa;
    }
    public void setPrograma(Programa programa) {
        this.programa = programa;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
