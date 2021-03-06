package es.org.greendaoexample.data.db;

import es.org.greendaoexample.data.db.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import es.org.greendaoexample.data.db.dao.ClienteDao;
import es.org.greendaoexample.data.db.dao.ViajeDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "VIAJE".
 */
public class Viaje {

    private Long id;
    private Long cliente_id;
    private String destino;
    private Integer dias;
    private java.util.Date fecha_reserva;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ViajeDao myDao;

    private Cliente cliente;
    private Long cliente__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Viaje() {
    }

    public Viaje(Long id) {
        this.id = id;
    }

    public Viaje(Long id, Long cliente_id, String destino, Integer dias, java.util.Date fecha_reserva) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.destino = destino;
        this.dias = dias;
        this.fecha_reserva = fecha_reserva;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getViajeDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public java.util.Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(java.util.Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    /** To-one relationship, resolved on first access. */
    public Cliente getCliente() {
        Long __key = this.cliente_id;
        if (cliente__resolvedKey == null || !cliente__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClienteDao targetDao = daoSession.getClienteDao();
            Cliente clienteNew = targetDao.load(__key);
            synchronized (this) {
                cliente = clienteNew;
            	cliente__resolvedKey = __key;
            }
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        synchronized (this) {
            this.cliente = cliente;
            cliente_id = cliente == null ? null : cliente.getId();
            cliente__resolvedKey = cliente_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
