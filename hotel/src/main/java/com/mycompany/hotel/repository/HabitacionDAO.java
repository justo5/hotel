
package com.mycompany.hotel.repository;

import com.mycompany.hotel.models.Habitacion;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.hotel.models.Habitacion;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAOImpl implements HabitacionDAO {
    private List<Habitacion> habitaciones = new ArrayList<>();

    @Override
    public Habitacion obtenerPorId(int id) {
        return habitaciones.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElse(null);
    }       

    @Override
    public List<Habitacion> obtenerTodas() {
        return habitaciones;
    }

    @Override
    public void guardar(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    @Override
    public void actualizar(Habitacion habitacion) {
        Habitacion existente = obtenerPorId(habitacion.getId());
        if (existente != null) {
            existente.setNumero(habitacion.getNumero());
            existente.setCamasSimples(habitacion.getCamasSimples());
            existente.setCamasDobles(habitacion.getCamasDobles());
            existente.setPrecioPorNoche(habitacion.getPrecioPorNoche());
        }
    }

    @Override
    public void eliminar(int id) {
        habitaciones.removeIf(h -> h.getId() == id);
    }
}

