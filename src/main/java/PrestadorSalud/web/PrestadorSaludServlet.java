package PrestadorSalud.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import PrestadorSalud.model.PrestadorSalud;
import PrestadorSalud.model.TipoPrestador;
import PrestadorSalud.repository.PrestadorSaludRepository;
import PrestadorSalud.service.PrestadorSaludService;

import java.io.IOException;
import java.util.List;

@WebServlet("/prestadores")
public class PrestadorSaludServlet extends HttpServlet {

    private PrestadorSaludService service;

    @Override
    public void init() throws ServletException {
        PrestadorSaludRepository repository = new PrestadorSaludRepository();
        service = new PrestadorSaludService(repository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        List<PrestadorSalud> prestadores = (nombre != null && !nombre.isBlank())
                ? service.buscarPorNombre(nombre)
                : service.obtenerPrestadores();
        req.setAttribute("prestadores", prestadores);
        req.getRequestDispatcher("/prestadores.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String direccion = req.getParameter("direccion");
        TipoPrestador tipo = TipoPrestador.valueOf(req.getParameter("tipo"));
        service.addPrestador(new PrestadorSalud(nombre, direccion, tipo));
        resp.sendRedirect(req.getContextPath() + "/prestadores");
    }
}
