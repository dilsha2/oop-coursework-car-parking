package util;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public interface LoadUI {
    void loadUi(String location,String title) throws IOException;


    void CloseWindowUi(AnchorPane x) throws IOException;
}