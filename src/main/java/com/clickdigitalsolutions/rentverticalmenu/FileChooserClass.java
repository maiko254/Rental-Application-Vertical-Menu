/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.beans.property.SimpleObjectProperty;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author bonyo
 */
public class FileChooserClass {
    public static FileChooser newInstance = null;
    public static SimpleObjectProperty<File> lastSelectedDirectoryProperty = new SimpleObjectProperty<>();
    public  static Path file = null;
    
    private FileChooserClass() {}
    
    private static FileChooser getInstance() {
        if (newInstance == null) {
            newInstance = new FileChooser();
            newInstance.initialDirectoryProperty().bindBidirectional(lastSelectedDirectoryProperty);
            newInstance.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Excel SpreadSheet", "*.xls"));
        }
        return newInstance;
    }
    
    public static File showOpenDialog() {
        return showOpenDialog();
    }
    
    public static File showOpenDialog(Window ownerWindow) {
        File selectedFile = getInstance().showOpenDialog(ownerWindow);
        if (selectedFile != null) {
            lastSelectedDirectoryProperty.setValue(selectedFile.getParentFile());
        }
        return selectedFile;
    }
    
    public static File showSaveDialog() {
        return showSaveDialog();
    }
    
    public static File showSaveDialog(Window ownerWindow) {
        File selectedFile = getInstance().showSaveDialog(ownerWindow);
        file = Paths.get(selectedFile.toURI());
        if (selectedFile != null) {
            lastSelectedDirectoryProperty.setValue(selectedFile.getParentFile());
        }
        return selectedFile;
    }
}
