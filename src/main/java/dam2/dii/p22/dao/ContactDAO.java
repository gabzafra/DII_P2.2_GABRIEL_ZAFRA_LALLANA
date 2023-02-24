package dam2.dii.p22.dao;

import java.util.List;
import dam2.dii.p22.model.Contacto;

public interface ContactDAO {
  public Contacto createContact(Contacto contact);

  public List<Contacto> getAllContacts();

  public Contacto getContactById(String id);

  public boolean deleteContactById(String id);

  public Contacto updateContact(Contacto contact);
}
