
package com.mycompany.hotel.mapper;

import com.mycompany.hotel.dto.ReservaDTO;
import com.mycompany.hotel.interfaz.IMapper;
import com.mycompany.hotel.model.Habitacion;
import com.mycompany.hotel.model.Pasajero;
import com.mycompany.hotel.model.Reserva;
import java.sql.Date;

/**
 * Clase encargada de realizar la conversión entre la entidad {@link Reserva} 
 * y su versión {@link ReservaDTO}.
 * <p>
 * Implementa la interfaz {@link IMapper} definiendo los métodos para:
 * <ul>
 *   <li>Transformar de entidad a DTO.</li>
 *   <li>Transformar de DTO a entidad.</li>
 * </ul>
 * </p>
 *
 * <p>Este mapper permite aislar la capa de presentación de la estructura interna
 * de las entidades y manejar datos simplificados para transferencias.</p>
 * 
 * @author Rocio
 */
public class ReservaMapper implements IMapper<Reserva,ReservaDTO> {
    
     /**
     * Convierte una entidad {@link Reserva} en su equivalente {@link ReservaDTO}.
     * <p>
     * Solo se incluyen los datos necesarios para transporte, como IDs de relaciones
     * y campos primitivos. No se cargan datos completos de {@link Pasajero} o {@link Habitacion}.
     * </p>
     *
     * @param entity entidad {@link Reserva} a convertir.
     * @return un {@link ReservaDTO} con la información de la reserva,
     *         o {@code null} si la entidad es nula.
     */
    @Override
    public ReservaDTO toDTO(Reserva entity) {
        if (entity == null) {
            return null;
        }

            ReservaDTO dto = new ReservaDTO();
            dto.setId(entity.getId());
            dto.setChekin((Date) entity.getChekin());
            dto.setCheckout((Date) entity.getCheckout());
            dto.setSenia(entity.getSenia());

            // Extraer solo los IDs de las entidades relacionadas
            if (entity.getoPasajero() != null)
                dto.setId_pasajero(entity.getoPasajero().getId());

            if (entity.getoHabitacion() != null)
                dto.setId_habitacion(entity.getoHabitacion().getId());

        return dto;
    }
    
     /**
     * Convierte un {@link ReservaDTO} en su equivalente entidad {@link Reserva}.
     * <p>
     * Se crean instancias mínimas de {@link Pasajero} y {@link Habitacion} con solo su ID.
     * Esto evita cargar información extra innecesaria en esta etapa.
     * </p>
     *
     * @param dto objeto {@link ReservaDTO} a convertir.
     * @return la entidad {@link Reserva} correspondiente,
     *         o {@code null} si el DTO es nulo.
     */
    @Override
    public Reserva toEntity(ReservaDTO dto) {
        if (dto == null) return null;

        Reserva entity = new Reserva();
        entity.setId(dto.getId());
        entity.setChekin(dto.getChekin());
        entity.setCheckout(dto.getCheckout());
        entity.setSenia(dto.getSenia());

        // Crear objetos mínimos con solo el ID (podés cargar el resto después si hace falta)
        Pasajero pasajero = new Pasajero();
        pasajero.setId(dto.getId_pasajero());
        entity.setoPasajero(pasajero);

        Habitacion habitacion = new Habitacion();
        habitacion.setId(dto.getId_habitacion());
        entity.setoHabitacion(habitacion);

        return entity;
    }
    
}
