package com.api.cat;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosServices {
    public static void verGatos() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
        Response response = client.newCall(request).execute();

        String elJson = response.body().string();

        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);

        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);

        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth() > 800){
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu = "Opciones: \n"
                    +" 1. Ver otra imagen.\n"
                    +" 2. Favorito.\n"
                    +" 3. Volver.\n";
            String[] botones = {"ver otra imagen","favorito","volver"};
            String idGato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,idGato, JOptionPane.INFORMATION_MESSAGE,fondoGato,botones,botones[0]);

            int seleccion = -1;
            for (int i = 0; i < botones.length; i++){
                if (opcion.equals(botones[i])){
                    seleccion = i;
                }
            }

            switch (seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    gatoFavorito(gatos);
                    break;
                default:
                    break;
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void gatoFavorito(Gatos gato){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+gato.getId()+"\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("x-api-key", gato.getApíKey())
                    .build();
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void verFavoritos(String apiKey) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application.json")
                .addHeader("x-api-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        String elJson = response.body().string();

        Gson gson = new Gson();

        GatosFav[] gatosArray = gson.fromJson(elJson,GatosFav[].class);

        if (gatosArray.length > 0){
            int min = 1;
            int max = gatosArray.length;
            int aleatoreo = (int) (Math.random() * ((max-min)+1)) + min;
            int indice = aleatoreo - 1;

            GatosFav gatosFav = gatosArray[indice];

            //Codigo copiado
            Image image = null;
            try {
                URL url = new URL(gatosFav.image.getUrl());
                image = ImageIO.read(url);
                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth() > 800){
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n"
                        +" 1. Ver otra imagen.\n"
                        +" 2. Eliminar favorito.\n"
                        +" 3. Volver.\n";
                String[] botones = {"ver otra imagen","favorito","volver"};
                String idGato = gatosFav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null,menu,idGato, JOptionPane.INFORMATION_MESSAGE,fondoGato,botones,botones[0]);

                int seleccion = -1;
                for (int i = 0; i < botones.length; i++){
                    if (opcion.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch (seleccion){
                    case 0:
                        verFavoritos(apiKey);
                        break;
                    case 1:
                        borrarFavorito(gatosFav);
                        break;
                    default:
                        break;
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }
    }

    public static void borrarFavorito(GatosFav gatoFav){
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites"+gatoFav.getId()+"")
                    .get()
                    .addHeader("Content-Type", "application.json")
                    .addHeader("x-api-key", gatoFav.getApikey())
                    .build();

            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
