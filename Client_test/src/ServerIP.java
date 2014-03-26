/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Klasse repraesentiert ein Domaenen - Adressen Paar.
 * @author Arax
 */
public class ServerIP {

    private String domain;
    private String adresse;

    /**
     * Konstruktor, erwartet einen String als Domain und einen String als Adresse.
     * @param domain
     * @param adresse 
     */
    public ServerIP(String domain, String adresse) {
        this.domain = domain;
        this.adresse = adresse;
    }
    
    public String getDomain(){
        return domain;
    }
    
    public String getAdresse(){
        return adresse;
    }
}
