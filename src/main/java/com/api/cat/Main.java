package com.api.cat;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int opcion_menu = -1;
        String[] botones = {"1. Ver Gatos", "2. Ver Favoritos","3. Salir"};

        do {
            String option = (String) JOptionPane.showInputDialog(null,"Gatitos Java","Menu Principal",JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);
            for(int i=0; i < botones.length; i++){
                if (option.equals(botones[i])){
                    opcion_menu = i;
                }
            }

            switch (opcion_menu){
                case 0:
                    GatosServices.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosServices.verFavoritos(gato.getApíKey());
                    break;
                default:
                    break;
            }
        }while (opcion_menu != 1);
    }
}
