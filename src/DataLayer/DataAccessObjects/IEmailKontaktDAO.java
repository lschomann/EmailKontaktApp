
package DataLayer.DataAccessObjects;


import Exceptions.*;
import BusinessObjects.IEmailKontakt;


/**
 * Interface for EmailKontaktDAO methods
 *
 * @author Lukas Schomann
 * @author Malte Engelhardt
 */
public interface IEmailKontaktDAO {
    public IEmailKontakt create();
    public IEmailKontakt[] select() throws NoEmailKontaktFoundException;
    public IEmailKontakt select(int id) throws NoEmailKontaktFoundException;
    public IEmailKontakt[] select(String criterion) throws NoEmailKontaktFoundException;
    public IEmailKontakt first() throws NoEmailKontaktFoundException ;
    public IEmailKontakt last() throws NoEmailKontaktFoundException ;
    public void delete(IEmailKontakt emailKontakt);
    public IEmailKontakt next(IEmailKontakt emailKontakt) throws NoNextEmailKontaktFoundException ;
    public IEmailKontakt previous(IEmailKontakt emailKontakt) throws NoPreviousEmailKontaktFoundException ;
    public void save(IEmailKontakt emailKontakt);
}