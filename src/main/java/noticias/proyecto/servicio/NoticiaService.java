package noticias.proyecto.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public List<Noticia> obtenerNoticiasDelDia() {
        List<Noticia> noticias = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();

        for (Noticia n : noticiaRepository.findAllByOrderByFechaPublicacionDesc()) {

            LocalDate fechaNoticia = n.getFechaPublicacion().toLocalDate();
            
            if (fechaHoy.equals(fechaNoticia)) {
                noticias.add(n);
            } else break;
        }

        return noticias;
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
