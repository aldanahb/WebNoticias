package noticias.proyecto.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import noticias.proyecto.modelo.Noticia;
import noticias.proyecto.servicio.NoticiaService;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/paginaPrincipal")
    public String paginaPrincipal(Model model) {

        List<Noticia> noticias = noticiaService.obtenerTodasLasNoticias();

        model.addAttribute("noticias", noticias);
        model.addAttribute("bannerLink", "https://www.turismoentrerios.com");
        model.addAttribute("bannerImagen", "/images/banner-turismo.jpg");
        model.addAttribute("bannerTitulo", "Visitá Entre Ríos");

        return "paginaPrincipal";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String intentarLogin(@RequestParam String usuario, 
                                @RequestParam String password, 
                                HttpSession session) {
        
        // Validación (puedes cambiar esto por una consulta a la DB)
        if ("admin".equals(usuario) && "A1B2C3789".equals(password)) {
            // GUARDAMOS LA SESIÓN: "usuarioLogueado" será nuestra marca
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/admin";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/admin")
    public String mostrarAdmin(HttpSession session, Model model) {
        
        // VERIFICACIÓN: ¿Hay alguien en la sesión?
        if (session.getAttribute("usuarioLogueado") == null) {
            // Si no hay nadie, lo mandamos al login
            return "redirect:/login";
        }
        
        // Si llega hasta acá, es porque está logueado
        List<Noticia> lista = noticiaService.obtenerTodasLasNoticias();
        model.addAttribute("noticias", lista);
        return "admin"; 
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidamos la sesión (borra todo)
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/cargarNoticia") 
    public String mostrarFormularioNoticia(HttpSession session) {
        // Verificar que el usuario esté logueado
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        return "cargarNoticia";
    }

    @PostMapping("/guardarNoticia") 
    public String gurdarNoticia(
    @RequestParam("titular") String titular,
    @RequestParam("volanta") String volanta,
    @RequestParam("bajada") String bajada,
    @RequestParam("copete") String copete,
    @RequestParam("cuerpo") String cuerpo,
    @RequestParam("epigrafe") String epigrafe,
    @RequestParam("tipo") String tipo,
    @RequestParam(value = "id", required = false) Integer id,
    @RequestParam(value = "imagen", required = false) MultipartFile imagen) {

        Noticia noticia;
        
        if(id != null) noticia = noticiaService.obtenerNoticia(id);
        else {
            noticia = new Noticia();
            noticia.setFechaPublicacion(LocalDate.now());
        }

        noticia.setTitulo(titular);
        noticia.setVolanta(volanta);
        noticia.setBajada(bajada);
        noticia.setCopete(copete);
        noticia.setCuerpo(cuerpo);
        noticia.setEpigrafe(epigrafe);
        noticia.setTipo(tipo);

        if (imagen != null && !imagen.isEmpty()) {
            try {
                String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
                Path ruta = Paths.get("src/main/resources/static/uploads/" + nombreArchivo);
                Files.createDirectories(ruta.getParent());
                Files.copy(imagen.getInputStream(), ruta);
                noticia.setImagen("/uploads/" + nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

        noticiaService.guardarNoticia(noticia);

        return "redirect:/admin";
        
    }

    @GetMapping("/editarNoticia/{id}") 
    public String mostrarFormularioEdicion(HttpSession session, @PathVariable Integer id, Model model) {

        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        Noticia noticiaEditar = noticiaService.obtenerNoticia(id);
        model.addAttribute("noticia", noticiaEditar);
        return "cargarNoticia";
    }

    @PostMapping("/eliminarNoticia/{id}")
    public String eliminarNoticia(HttpSession session, @PathVariable Integer id) {

        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        noticiaService.eliminarNoticia(id);

        return "redirect:/admin";
    }

    @GetMapping("/noticia/{id}")
    public String mostrarNoticia(@PathVariable Integer id, Model model) {
        Noticia noticiaMostrar = noticiaService.obtenerNoticia(id);
        model.addAttribute("noticia", noticiaMostrar);
        return "noticia";
    }

    @GetMapping("/seccion/{tipo}")
    public String verSeccion(@PathVariable String tipo, Model model) {
        String tipoCapitalizado = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();
        List<Noticia> noticias = noticiaService.obtenerNoticiasPorTipo(tipoCapitalizado);
        model.addAttribute("noticias", noticias);
        model.addAttribute("seccion", tipoCapitalizado);
        return "seccion";
    }

}
