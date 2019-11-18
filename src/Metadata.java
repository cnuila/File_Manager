
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Carlos Nuila
 */
public class Metadata {

    private ArrayList<Campo> campos = new ArrayList<>();

    public Metadata() {
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    public void cargarCampos(File archivo) {
        Scanner sc = null;
        campos = new ArrayList<>();
        if (archivo.exists()) {
            try {
                sc = new Scanner(archivo);
                sc.useDelimiter(";");
                while (sc.hasNext()) {
                    String prueba = sc.next();
                    System.out.println(prueba);
                }
            } catch (Exception e) {
            }
            sc.close();
        }
    }

}
