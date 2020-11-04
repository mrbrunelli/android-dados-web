package com.example.enviardadosweb.classes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class EnviarDadosWeb extends AsyncTask<String, Void, HashMap<String, String>> {

    @Override
    protected HashMap<String, String> doInBackground(String... strings) {
        String link = "http://marcosdiasvendramini.com.br/Set/Aula.aspx?texto=" + codificaParametro(strings[0]);
        HashMap<String, String> itemRetorno = new HashMap<>();
        try {
            URL url = new URL(link); // transforma o link string em uma URL valida
            URLConnection connection = url.openConnection(); // abre a conexao da URL
            InputStreamReader reader = new InputStreamReader(connection.getInputStream()); // captura os dados que retornaram da URL
            BufferedReader bufferedReader = new BufferedReader(reader); // pega os dados e converte em formato para leitura
            String linha = bufferedReader.readLine(); // pegando o retorno da web e salvando na string linha
            JSONArray ja = new JSONArray(linha); // converte o texto recebido em um array de JSON
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = (JSONObject) ja.get(i); // captura o item do JSON na posicao i do loop
                itemRetorno.put("texto", jo.getString("texto")); // salva dentro do HashMap o valor do item JSON
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return itemRetorno;
    }

    private String codificaParametro(String parametro) {
        try {
            return URLEncoder.encode(parametro, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
