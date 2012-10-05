/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects;

import Exceptions.*;


/**
 *
 * @author lschomann
 */
public interface IEmailKontaktDAO {
    public BusinessObjects.IEmailKontakt create();
    public BusinessObjects.IEmailKontakt[] select();
    public BusinessObjects.IEmailKontakt select(int id) throws NoEmailKontaktFoundException;
    public void first();
    public void last();
    public void delete(BusinessObjects.IEmailKontakt emailKontakt);
    public void next(BusinessObjects.IEmailKontakt emailKontakt);
    public void previous(BusinessObjects.IEmailKontakt emailKontakt);
    public void save(BusinessObjects.IEmailKontakt emailKontakt);
}
