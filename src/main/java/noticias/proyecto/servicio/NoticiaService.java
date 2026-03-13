package noticias.proyecto.servicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Noticia> obtenerNoticiasPorTipo(String tipo) {
        return noticiaRepository.findByTipoIgnoreCase(tipo);
    }

    public void eliminarNoticia(int id) {
        noticiaRepository.deleteById(id);
    }

    public Noticia obtenerNoticia(int id) {
        Noticia noticia = noticiaRepository.findById(id).getFirst();
        return noticia;
    }


}
