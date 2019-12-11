
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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
    private int tamanoMeta = 0;

    public Metadata() {
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<Campo> campos) {
        this.campos = campos;
    }

    public int getTamanoMeta() {
        return tamanoMeta;
    }

    public void setTamanoMeta(int tamanoMeta) {
        this.tamanoMeta = tamanoMeta;
    }

    public void escribirCampos(RandomAccessFile archivo) throws IOException {
        archivo.setLength(0);
        int pos = 0;
        int acum = 0;
        String saltoLinea = "\n";
        for (int i = 0; i < campos.size(); i++) {
            archivo.seek(pos);
            archivo.write(campos.get(i).toString().getBytes());
            String delimitador = ";";
            archivo.write(delimitador.getBytes());
            pos += campos.get(i).toString().length() + 1;
            acum += campos.get(i).toString().length() + 1;
            if (acum >= 50 && i != campos.size() - 1) {
                archivo.write(saltoLinea.getBytes());
                pos++;
                acum = 0;
            }
        }
        archivo.write(saltoLinea.getBytes());
        pos++;
        String finalMetaData = "***\n";
        archivo.seek(pos);
        archivo.write(finalMetaData.getBytes());
        pos += 4;
        tamanoMeta = pos;
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
        for (int i = 0; i < ubicAst - 2; i++) {
            if (tempString.charAt(i) != '\n') {
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
                    index++;
                    j = campo.length();
                }
            }
            String tempTamano = "";
            for (int j = index; j < campo.length(); j++) {
                if (campo.charAt(j) != ']') {
                    tempTamano += campo.charAt(j);
                    index++;
                } else {
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
        if (!campos.isEmpty()) {
            tamanoMeta = ubicAst + 3;
        }
    }

}
