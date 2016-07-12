package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * @author Óscar Rodríguez <oscar.garrucho@gmail.com>
 * @since 22/6/16 16:18
 */
public class MainGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {

        // Prueba inicial para generar el código de las entidades
        createConfigSchema();
    }

    private static void createConfigSchema() {

        int schemaVersion = 1;   // incrementar en cada nueva actualización del esquema.
        String dataPackage = "es.org.greendaoexample.data.db";   // ruta donde almacenar las clases-entidades.

        Schema configSchema = new Schema(schemaVersion, dataPackage);
        configSchema.setDefaultJavaPackageDao(dataPackage + ".dao");
        configSchema.enableKeepSectionsByDefault();   // con esto no sobreescribe código personal añadido en las clases de entidades.

        addTables(configSchema);

        try {
            new DaoGenerator().generateAll(configSchema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(Schema schema) {

        /* entidades */

        Entity cliente = schema.addEntity("Cliente");   // nombre de la tabla-entidad
        cliente.addIdProperty().primaryKey().autoincrement();   // columna id
        cliente.addStringProperty("dni").notNull().unique();
        cliente.addStringProperty("nombre").notNull();
        cliente.addStringProperty("apellidos");   // puede ser NULL
        cliente.addStringProperty("direccion");
        cliente.addIntProperty("edad");

        Entity viaje = schema.addEntity("Viaje");
        viaje.addIdProperty().primaryKey().autoincrement();
        Property clienteId = viaje.addLongProperty("cliente_id").index().getProperty();   // clave foránea
        viaje.addStringProperty("destino");
        viaje.addIntProperty("dias");
        viaje.addDateProperty("fecha_reserva");


        // Relaciones (tipo -> 1:N)

        // un viaje sólo puede pertenecer a un cliente, pero un cliente puede realizar varios viajes.
        viaje.addToOne(cliente, clienteId);
        cliente.addToMany(viaje, clienteId);
    }
}
