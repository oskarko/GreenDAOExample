package es.org.greendaoexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Date;
import java.util.List;
import es.org.greendaoexample.data.db.Cliente;
import es.org.greendaoexample.data.db.Viaje;
import es.org.greendaoexample.data.db.dao.ClienteDao;
import es.org.greendaoexample.data.db.dao.DaoMaster;
import es.org.greendaoexample.data.db.dao.DaoSession;
import es.org.greendaoexample.data.db.dao.ViajeDao;

/**
 * @author Óscar Rodríguez <oscar.garrucho@gmail.com>
 * @since 22/6/16 16:14
 */
public class MainActivity extends AppCompatActivity {

    // greenDao
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ClienteDao clienteDao;
    private ViajeDao viajeDao;

    // botones
    Button btnAdd, btnShow, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializamos la base de datos
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        clienteDao = daoSession.getClienteDao();
        viajeDao = daoSession.getViajeDao();

        // asociamos los botones
        btnAdd = (Button) findViewById(R.id.btnAddData);
        btnShow = (Button) findViewById(R.id.btnShowData);
        btnDelete = (Button) findViewById(R.id.btnDeleteData);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    // creamos un nuevo cliente y un nuevo viaje. Asociamos el cliente al viaje y los guardamos en la base de datos.
    public void add(){

        Cliente oskarko = new Cliente();
        oskarko.setDni("12345678Z");
        oskarko.setNombre("Oskarko");

        clienteDao.insert(oskarko);

        Viaje eeuu = new Viaje();
        eeuu.setDestino("New York");    // I wish!
        eeuu.setDias(10);
        eeuu.setFecha_reserva(new Date());
        eeuu.setCliente(oskarko);   // asociamos el cliente (primeramente guardado en BD) al viaje

        viajeDao.insert(eeuu);
    }

    public void show(){

        List viajes = viajeDao.queryBuilder()
                .where( ViajeDao.Properties.Destino.eq("New York") )
                .list();

        if (viajes.isEmpty()){
            System.out.println("Oops! Base de datos vacía.");
        }
        else {
            // imprimimos por consola los datos almacenados en la base de datos.
            for (int i = 0; i < viajes.size(); i++) {

                Viaje miViaje = (Viaje) viajes.get(i);
                System.out.println(miViaje.getCliente().getNombre() + ", " + miViaje.getDestino());
            }
        }
    }

    public void delete(){

        // el orden SÍ importa, al revés da error.
        clienteDao.deleteAll();
        viajeDao.deleteAll();
    }

    // Si pulsamos dos veces seguidas sobre el botón de "Añadir Datos" la app se cerrará
    // debido a una excepción. Esto es debido a que el campo DNI es ÚNICO y no puede repetirse.
    // Lo ideal sería comprobar que un DNI no está en la base de datos ANTES de guardar un nuevo cliente.
}
