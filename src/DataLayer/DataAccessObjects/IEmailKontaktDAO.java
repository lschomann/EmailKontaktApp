/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer.DataAccessObjects;

import Exceptions.*;
import BusinessObjects.IEmailKontakt;


/**
 *
 * @author lschomann
 */
public interface IEmailKontaktDAO {
    public IEmailKontakt create();
    public IEmailKontakt[] select();
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException;
    public IEmailKontakt first();
    public IEmailKontakt last();
    public void delete(IEmailKontakt emailKontakt);
    public IEmailKontakt next(IEmailKontakt emailKontakt);
    public IEmailKontakt previous(IEmailKontakt emailKontakt);
    public void save(IEmailKontakt emailKontakt);
}