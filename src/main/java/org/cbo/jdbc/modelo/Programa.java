package org.cbo.jdbc.modelo;

public class Programa {
    
    private String nombre;
    private String codigo;
    private String version; 
    private String estado;

    

    public Programa(String nombre, String codigo, String version, String estado, Long id) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.version = version;
        this.estado = estado;
        this.id = id;
    }

    

    public Programa() {
    }



    private Long id;
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
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
  

    

}
