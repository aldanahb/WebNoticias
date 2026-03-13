package noticias.proyecto.repositorio;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import noticias.proyecto.modelo.Noticia;

@Repository
public interface NoticiaRepository extends CrudRepository<Noticia,Integer> {

    List<Noticia> findByTipoIgnoreCase(String tipo);
    List<Noticia> findById(int id);
    List<Noticia> findAllByOrderByFechaPublicacionDesc();
}
