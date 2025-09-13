package PrestadorSalud.repository;

import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Singleton;
import PrestadorSalud.model.PrestadorSalud;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Local(PrestadorSaludRepositoryLocal.class)
@Remote(PrestadorSaludRepositoryRemote.class)
public class PrestadorSaludRepository implements PrestadorSaludRepositoryLocal, PrestadorSaludRepositoryRemote {

    private final List<PrestadorSalud> prestadores = new ArrayList<>();
    private long nextId = 1;

    @Override
    public void add(PrestadorSalud prestador) {
        prestador.setId(nextId++);
        prestadores.add(prestador);
    }

    @Override
    public List<PrestadorSalud> getAll() {
        return new ArrayList<>(prestadores);
    }

    @Override
    public List<PrestadorSalud> findByNombre(String nombre) {
        return prestadores.stream()
                .filter(p -> p.getNombre() != null && p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}
