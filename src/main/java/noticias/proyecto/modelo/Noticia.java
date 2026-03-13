package noticias.proyecto.modelo;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;
    private String volanta;
    private String titulo;
    private String bajada;
    private String copete;
    private String cuerpo;
    private String imagen; // nombre del archivo de la imagen. Esto lo trae del servidor (resources/static)
    private String epigrafe;
    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    

}
