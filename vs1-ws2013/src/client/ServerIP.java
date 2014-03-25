/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Arax
 */
public class ServerIP {

    private String name;
    private String adresse;

    public ServerIP(String name, String adresse) {
        this.name = name;
        this.adresse = adresse;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAdresse(){
        return adresse;
    }
}
