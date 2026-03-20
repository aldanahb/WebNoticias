package noticias.proyecto.modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;

    @Column(length = 150)
    private String volanta;

    @Column(length = 300)
    private String titulo;

    @Column(length = 500)
    private String bajada;

    @Column(columnDefinition = "TEXT")
    private String copete;

    @Column(columnDefinition = "TEXT")
    private String cuerpo;
    
    private String imagen;
    private String imagenPublicId; // identificador que Cloudinary le asigna al subir

    @Column(length = 300)
    private String epigrafe;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    private String imagenPosicion = "50% 50%";

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVolanta() {
        return volanta;
    }

    public void setVolanta(String volanta) {
        this.volanta = volanta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBajada() {
        return bajada;
    }

    public void setBajada(String bajada) {
        this.bajada = bajada;
    }

    public String getCopete() {
        return copete;
    }

    public void setCopete(String copete) {
        this.copete = copete;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEpigrafe() {
        return epigrafe;
    }

    public void setEpigrafe(String epigrafe) {
        this.epigrafe = epigrafe;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getImagenPublicId() {
        return imagenPublicId;
    }

    public void setImagenPublicId(String imagenPublicId) {
        this.imagenPublicId = imagenPublicId;
    }

    public String getImagenPosicion() {
        return imagenPosicion;
    }

    public void setImagenPosicion(String imagenPosicion) {
        this.imagenPosicion = imagenPosicion;
    }

}
