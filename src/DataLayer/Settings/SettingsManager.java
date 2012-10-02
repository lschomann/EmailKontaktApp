/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.Settings;

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
    public String getPersistenceType(){
        return persistenceSettings.getPersistenceType();
                
    }
}
