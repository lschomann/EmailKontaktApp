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
public class SettingsManager {
    private SettingsManager instance;
    private PersistenceSettings persistenceSettings;
    
    private void SettingsManager(){
        
    }
    private PersistenceSettings readPersistenceSettings(){
        return persistenceSettings;
    }
    public SettingsManager getInstance(){
        return instance;
    }
    public String getPersistenceType() throws XPathExpressionException, FileNotFoundException{
        return persistenceSettings.getPersistenceType();
                
    }
}
