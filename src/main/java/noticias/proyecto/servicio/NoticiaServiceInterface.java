package noticias.proyecto.servicio;

import java.util.List;

import noticias.proyecto.modelo.Noticia;

public interface NoticiaServiceInterface {

    public Noticia obtenerNoticia(int id);
    public Noticia guardarNoticia(Noticia noticia);
    public List<Noticia> obtenerTodasLasNoticias(); // devolver en orden (más reciente)
    public List<Noticia> obtenerNoticiasPorTipo(String tipo);
    public void eliminarNoticia(int id);

}
