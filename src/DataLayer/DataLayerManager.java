
package DataLayer;

import DataLayer.Settings.SettingsManager;


/**
 *
 *
 * @author lschomann
 */
public class DataLayerManager {
    
	/** Privates Klassenattribut, einzige Instanz der Klasse erzeugen. */
    private static DataLayerManager instance = new DataLayerManager();
    private IDataLayer dataLayer;
    
    /** Konstruktor ist privat, darf nicht von außen instanziiert werden. */
    private DataLayerManager(){
        getDataLayer();
    }
    
    /** Statische Methode „getInstance()“ liefert die einzige Instanz der Klasse zurück. */
    public static DataLayerManager getInstance(){
        return instance;
    }
    
    /** Fetch the DataLayer from SettingsManager */
    public IDataLayer getDataLayer(){
    	SettingsManager _instance = SettingsManager.getInstance();
        return _;
    }
}
