/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naufal.pustaka.service;

import com.google.gson.Gson;
import com.naufal.pustaka.model.Anggota;
import java.util.List;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 *
 * @author IIISI
 */
public class AnggotaService {
    private final String URL = "http://localhost:9001";
    
    public Anggota getAnggota(Long anggotaId){
        Anggota anggota = Unirest.get(URL+"/anggota/"+anggotaId).asObject(Anggota.class).getBody();
        if (anggota!=null) {
            return anggota;
        }
        return null;
    }
    
    public List<Anggota> getAllAnggota(){
        List<Anggota> anggotaList = Unirest.get(URL + "/anggota/")
                .asObject(new GenericType<List<Anggota>> (){}).getBody();
        return anggotaList;
    }
    
    public Anggota saveAnggota(Anggota anggota){
        HttpResponse<JsonNode> response = Unirest.post(URL + "/anggota/")
                .header("content-type","application/json")
                .body(anggota).asJson();
        Gson gson = new Gson();
        Anggota a = gson.fromJson(response.getBody().toString(), Anggota.class);
        return a;
    }
    
    public Anggota updateAnggota(Anggota anggota){
        HttpResponse<JsonNode> response = Unirest.put(URL + "/anggota/")
                .header("content-type","application/json")
                .body(anggota).asJson();
        Gson gson = new Gson();
        Anggota a = gson.fromJson(response.getBody().toString(), Anggota.class);
        return a;
    }
    
    public void deleteAnggota(Long anggotaId){
        Unirest.delete(URL + "/anggota/"+anggotaId).asEmpty();
    }
}
