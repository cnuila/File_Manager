
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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

    public void cargarCampos(RandomAccessFile archivo) throws IOException {
        campos = new ArrayList<>();
        byte[] temp = new byte[400];
        int ubicAst = 0;
        for (int i = 0; i < archivo.length(); i++) {
            archivo.seek(i);
            if (!new String(temp).contains("*")) {
                temp[i] = archivo.readByte();
            } else {
                ubicAst = i;
                break;
            }
        }
        String tempString = new String(temp);
        String metaDataTemp = "";
        for (int i = 0; i < ubicAst-2; i++) {
            if (tempString.charAt(i) != '\n'){
                metaDataTemp += tempString.charAt(i);
            }
        }
        String[] metaData = metaDataTemp.split(";");
        for (int i = 0; i < metaData.length; i++) {
            String campo = metaData[i];
            String nombreCampo = "";
            String tipoCampo = "";
            int tamano = 0;
            boolean llavePrimaria = false;
            int index = 0;
            for (int j = index; j < campo.length(); j++) {
                if (campo.charAt(j) != ':') {
                    nombreCampo += campo.charAt(j);
                    index++;
                } else {
                    index += 2;
                    j = campo.length();
                }
            }
            for (int j = index; j < campo.length(); j++) {
                if (campo.charAt(j) != '[') {
                    tipoCampo += campo.charAt(j);
                    index++;
                } else {
                    index ++;
                    j = campo.length();
                }
            }
            String tempTamano = "";
            for (int j = index;j < campo.length();j++) {
                if (campo.charAt(j) != ']'){
                    tempTamano+=campo.charAt(j);
                    index++;
                }else{
                    index++;
                    j = campo.length();
                }
            }
            tamano = Integer.parseInt(tempTamano);
            if (campo.charAt(index) == '0') {
                llavePrimaria = true;
            }
            campos.add(new Campo(nombreCampo, tipoCampo, tamano, llavePrimaria));
        }

    }

}
