package es.org.greendaoexample.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import es.org.greendaoexample.data.db.Cliente;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLIENTE".
*/
public class ClienteDao extends AbstractDao<Cliente, Long> {

    public static final String TABLENAME = "CLIENTE";

    /**
     * Properties of entity Cliente.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Dni = new Property(1, String.class, "dni", false, "DNI");
        public final static Property Nombre = new Property(2, String.class, "nombre", false, "NOMBRE");
        public final static Property Apellidos = new Property(3, String.class, "apellidos", false, "APELLIDOS");
        public final static Property Direccion = new Property(4, String.class, "direccion", false, "DIRECCION");
        public final static Property Edad = new Property(5, Integer.class, "edad", false, "EDAD");
    };

    private DaoSession daoSession;


    public ClienteDao(DaoConfig config) {
        super(config);
    }
    
    public ClienteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLIENTE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"DNI\" TEXT NOT NULL UNIQUE ," + // 1: dni
                "\"NOMBRE\" TEXT NOT NULL ," + // 2: nombre
                "\"APELLIDOS\" TEXT," + // 3: apellidos
                "\"DIRECCION\" TEXT," + // 4: direccion
                "\"EDAD\" INTEGER);"); // 5: edad
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLIENTE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Cliente entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDni());
        stmt.bindString(3, entity.getNombre());
 
        String apellidos = entity.getApellidos();
        if (apellidos != null) {
            stmt.bindString(4, apellidos);
        }
 
        String direccion = entity.getDireccion();
        if (direccion != null) {
            stmt.bindString(5, direccion);
        }
 
        Integer edad = entity.getEdad();
        if (edad != null) {
            stmt.bindLong(6, edad);
        }
    }

    @Override
    protected void attachEntity(Cliente entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Cliente readEntity(Cursor cursor, int offset) {
        Cliente entity = new Cliente( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // dni
            cursor.getString(offset + 2), // nombre
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // apellidos
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // direccion
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // edad
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Cliente entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDni(cursor.getString(offset + 1));
        entity.setNombre(cursor.getString(offset + 2));
        entity.setApellidos(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDireccion(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEdad(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Cliente entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Cliente entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
