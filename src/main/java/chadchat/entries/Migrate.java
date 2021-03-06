package chadchat.entries;

import chadchat.db.DBConnect;
import org.apache.ibatis.jdbc.ScriptRunner;


import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;


public class Migrate {

    public static void runMigrations() throws IOException, SQLException {
        int version = DBConnect.getCurrentVersion();
        while (version < DBConnect.getVersion()) {
            System.out.printf("Current DB version %d is smaller than expected %d\n", version, DBConnect.getVersion());
            runMigration(version + 1);
            int new_version = DBConnect.getCurrentVersion();
            if (new_version > version) {
                version = new_version;
                System.out.println("Updated database to version: " + new_version);
            } else {
                throw new RuntimeException("Something went wrong, version not increased: " + new_version);
            }
        }
    }

    public static void runMigration(int i) throws IOException, SQLException {
        String migrationFile = String.format("migrate/%d.sql", i);
        System.out.println("Running migration: " + migrationFile);
        InputStream stream = Migrate.class.getClassLoader().getResourceAsStream(migrationFile);
        if (stream == null) {
            System.out.println("Migration file, does not exist: " + migrationFile);
            throw new FileNotFoundException(migrationFile);
        }
        try(Connection conn = DBConnect.getConnection()) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        }
        System.out.println("Done running migration");
    }

}
