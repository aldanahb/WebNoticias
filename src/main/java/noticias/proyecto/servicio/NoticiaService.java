package noticias.proyecto.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import noticias.proyecto.modelo.Noticia;
import noticias.proyecto.repositorio.NoticiaRepository;

@Service
public class NoticiaService implements NoticiaServiceInterface {

    @Autowired 
    NoticiaRepository noticiaRepository;

    public Noticia guardarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public List<Noticia> obtenerTodasLasNoticias() {
        List<Noticia> noticias = new ArrayList<>();

        for (Noticia n : noticiaRepository.findAllByOrderByFechaPublicacionDesc()) {
            noticias.add(n);
        }

        return noticias;
    }

    public List<Noticia> obtenerNoticiasRecientes() { 
        List<Noticia> todasLasNoticias = obtenerTodasLasNoticias();
        List<Noticia> noticiasDeHoy = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();

        for (Noticia n : todasLasNoticias) {

            LocalDate fechaNoticia = n.getFechaPublicacion().toLocalDate();
            
            System.out.println(fechaHoy); 
            System.out.println(fechaNoticia);
            
            if (fechaHoy.equals(fechaNoticia)) {
                noticiasDeHoy.add(n);
            } else break;
        }

        if(!noticiasDeHoy.isEmpty()) return noticiasDeHoy; // si hay noticias de hoy, se devuelven esas

        else return todasLasNoticias.stream().limit(7).collect(Collectors.toList()); // si no hay noticias de hoy, se devuelven las últimas 7 cargadas
    }

    public List<Noticia> obtenerNoticiasPorTipo(String tipo) {
        return noticiaRepository.findByTipoIgnoreCaseOrderByFechaPublicacionDesc(tipo);
    }

    public void eliminarNoticia(int id) {
        noticiaRepository.deleteById(id);
    }

    public Noticia obtenerNoticia(int id) {
        Noticia noticia = noticiaRepository.findById(id).getFirst();
        return noticia;
    }


}
