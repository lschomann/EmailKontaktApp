/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

/**
 *
 * @author lschomann
 */
public class DataLayerManager {
    
    private DataLayerManager instance;
    private DataLayer.IDataLayer dataLayer;
    
    private DataLayerManager(){
        
    }
    public DataLayerManager getInstance(){
        return this.instance;
    }
    public IDataLayer getDataLayer(){
        return this.dataLayer;
    }
}
