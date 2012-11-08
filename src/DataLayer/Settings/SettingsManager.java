/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Settings;

import java.io.FileNotFoundException;

import javax.xml.xpath.XPathExpressionException;

/**
 *
 * @author lschomann
 */
public final class SettingsManager {
	
	/** Privates Klassenattribut, einzige Instanz der Klasse erzeugen. */
    private final static SettingsManager instance = new SettingsManager();
    private PersistenceSettings persistenceSettings;
    
    /** Konstruktor ist privat, darf nicht von außen instanziiert werden. */
    private void SettingsManager(){
        
    }
    
    private PersistenceSettings readPersistenceSettings(){
        return persistenceSettings;
    }
    
    /** Statische Methode „getInstance()“ liefert die einzige Instanz der Klasse zurück. */
    public static SettingsManager getInstance(){
        return instance;
    }
    
    public String getPersistenceType() throws XPathExpressionException, FileNotFoundException{
        return persistenceSettings.getPersistenceType();
                
    }
}
