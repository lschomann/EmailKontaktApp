package DataLayer.DataAccessObjects.Webservice;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import DataLayer.BusinessObjects.EmailKontakt;


public class EmailKontaktAdapter extends XmlAdapter<AdaptedEmailKontakt, EmailKontakt>{
    @Override
    public EmailKontakt unmarshal(AdaptedEmailKontakt adaptedEmailContact) throws Exception {
        return new EmailKontakt(adaptedEmailContact.getID(), adaptedEmailContact.getVorname(), 
        							adaptedEmailContact.getNachname(), adaptedEmailContact.getEmail());
    }
 
    @Override
    public AdaptedEmailKontakt marshal(EmailKontakt c) throws Exception {
    	AdaptedEmailKontakt adaptedC = new AdaptedEmailKontakt();
    	adaptedC.setID(c.getID());
    	adaptedC.setNachname(c.getNachname());
        adaptedC.setVorname(c.getVorname());
        adaptedC.setEmail(c.getEmail());
        return adaptedC;
    }
}
