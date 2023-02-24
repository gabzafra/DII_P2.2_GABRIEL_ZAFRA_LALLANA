package dam2.dii.p22.service;

import java.util.List;
import java.util.regex.Pattern;
import dam2.dii.p22.dao.ContactDAO;
import dam2.dii.p22.dao.ContactDAOXml;
import dam2.dii.p22.model.Contacto;

public class ContactoService {
  // private ContactDAO DAO = ContactDAOinMem.getInstance();
  private ContactDAO DAO = ContactDAOXml.getInstance();

  public Contacto getContactoById(String id) {
    return DAO.getContactById(id);
  }

  public List<Contacto> getAllContacts() {
    return DAO.getAllContacts();
  }

  public boolean deleteContactoById(String id) {
    return DAO.deleteContactById(id);
  }

  public Contacto createContacto(Contacto contact) {
    Contacto newContact = null;
    if (!hasEmptyFields(contact)) {
      newContact = DAO.createContact(contact);
    }
    if (newContact != null) {
      return newContact;
    } else {
      contact.setId("");
      return contact;
    }
  }

  private boolean hasEmptyFields(Contacto c) {
    return c.getName().equals("") || c.getSurnames().equals("") || c.getEmail().equals("")
        || c.getPhone().equals("") || c.getComents().equals("");
  }

  public Contacto updateContacto(Contacto contact) {
    Contacto newContact = null;
    if (!hasEmptyFields(contact)) {
      newContact = DAO.createContact(contact);
    }
    if (newContact != null) {
      return newContact;
    } else {
      contact.setId("");
      return contact;
    }
  }

  public String getContactsTotal() {
    return String.valueOf(DAO.getAllContacts().size());
  }

  public boolean validateEmail(Contacto contact) {
    String regex =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    return Pattern.compile(regex).matcher(contact.getEmail()).matches();
  }

}
