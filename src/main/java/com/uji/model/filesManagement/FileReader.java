package com.uji.model.filesManagement;

import com.uji.model.dataStructure.Table;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Lector de ficheros del classpath. Usa {@code getResourceAsStream}, así que también funciona
 * cuando la aplicación se empaqueta en un .jar.
 */
public abstract class FileReader<T extends Table> extends ReaderTemplate<T> {

    protected Scanner scanner;

    @Override
    protected void openSource(String source) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(source);
        if (in == null)
            throw new IllegalArgumentException("No se encuentra el fichero: " + source);
        scanner = new Scanner(in, StandardCharsets.UTF_8);
    }

    @Override
    protected boolean hasMoreData() {
        return (scanner != null) && (scanner.hasNextLine());
    }

    @Override
    protected String getNextData() {
        return scanner.nextLine();
    }

    @Override
    protected void closeSource() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
