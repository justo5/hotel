package com.mycompany.hotel.controlles;

import com.mycompany.hotel.DAO.HabitacionDAO;
import com.mycompany.hotel.interfaces.Icrud;
import com.mycompany.hotel.models.Habitacion;
import com.mycompany.hotel.models.Pasajero;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para manejar operaciones CRUD de Habitacion.
 * Implementa la interfaz Icrud para estandarizar los métodos.
 * Utiliza HabitacionDAO para interactuar con la base de datos.
 */
/**
 *
 * @author justcode
 */
public class HabitacionController implements Icrud<Habitacion> {
    
    private static HabitacionDAO habitacionDAO = HabitacionDAO.getInstancia();// Instancia única de HabitacionDAO para acceso a datos
    private Habitacion habitacion;// Objeto Habitacion que puede ser usado temporalmente
    public HabitacionController(){}// Constructor vacio de HabitacionController
    
    /**
     * Crea una nueva habitación en la base de datos usando un objeto Habitacion.
     * Utiliza el bloque dentro del 'try' para crear y guardar la habitación.
     * Utiliza el bloque del 'catch' por si falla para crear un mensaje y mostrarlo.
     * @param dato :  Objeto Habitacion con los datos para crear.
     */
     @Override
      public void crear(Habitacion dato) {
        try{
            habitacionDAO.crear(dato);
            
        }catch(Exception ex){
            System.out.println("No pudo ser creada" + ex);
        }
        
    }
     /** 
      * Crea una nueva habitación usando parámetros individuales.
      * @param numero : Número identificador de la habitación.
      * @param camasSimples : Cantidad de camas simples.
      * @param camasDobles : Cantidad de camas dobles.
      * @param precioPorNoche : Precio por noche.
      * @throws SQLException : Si ocurre un error con la base de datos.
      */
    public void crear(String numero, int camasSimples, int camasDobles, float precioPorNoche)throws SQLException {
        this.habitacion = new Habitacion();
        habitacionDAO.crear(habitacion);
    }
    
    /**
     * Actualiza la información de una habitación con el id especificado.
     * @param dato : Objeto Habitacion con los nuevos datos.
     * @param id : Identificador de la habitación a actualizar.
     * @throws SQLException : Si ocurre un error en la base de datos.
     */
    @Override
    public void actualizar(Habitacion dato, int id) throws SQLException {
     try {
            habitacionDAO.actualizar(dato, id);
           } catch (Exception ex) {
            System.out.println("Error al actualizar la habitación: " + ex);
        }
    }
    
    /**
     * Borra una habitación específica por objeto.
     * @param dato : Objeto habitación a eliminar.
     * @throws SQLException : Si ocurre un error con la base de datos.
     */
    @Override
    public void borrar(Habitacion dato) throws SQLException {
            habitacionDAO.borrar(dato);
    }
    
    /**
     * Borra una habitación especifica con id.
     * @param id : Identificador único de la habitación a borrar
     * @throws SQLException : Si ocurre un error con la base de datos.
     */
    @Override
    public void borrar(int id) throws SQLException {
    habitacionDAO.borrar(id);
    }
    
    /**
     * Recuperar una habitacion por su id.
     * @param id : Identificador de la habitación a recuperar.
     * @return : Retorna la habitación encontrada o null si no existe.
     * @throws SQLException : Si ocurre un error con la base de datos.
     */
    @Override
    public Habitacion recuperarPorId(int id) throws SQLException {
     return  habitacionDAO.recuperarPorId(id);
    }
    
    
    /**
     * Recupera todas las habitaciones registradas.
     * @return : Retorna una lista con todas las habitaciones
     * @throws SQLException : Si ocurre un error con la base de datos.
     */
    @Override
    public List<Habitacion> recuperarTodos() throws SQLException {
    return this.habitacionDAO.recuperarTodos();
    }
 
}
