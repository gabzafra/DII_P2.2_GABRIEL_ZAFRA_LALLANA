package dam2.dii.p22.service;

import java.util.List;
import java.util.regex.Pattern;
import javax.ws.rs.core.Response.Status;
import dam2.dii.p22.dao.ContactDAO;
import dam2.dii.p22.dao.ContactDAOinMem;
import dam2.dii.p22.model.Contacto;

public class ContactoService {
  private ContactDAO DAO = ContactDAOinMem.getInstance();
  // private ContactDAO DAO = ContactDAOXml.getInstance();

  public Contacto getContactoById(String id) {
    return DAO.getContactById(id);
  }

  public List<Contacto> getAllContacts() {
    return DAO.getAllContacts();
  }

  public boolean deleteContactoById(String id) {
    return DAO.deleteContactById(id);
  }

  private boolean isValidMail(Contacto contact) {
    Contacto existingContact = DAO.getAllContacts().stream()
        .filter(c -> c.getEmail().equals(contact.getEmail())).findFirst().orElse(new Contacto());
    if (existingContact.getId().equals("")) {
      // No existe el email en la bd
      return true;
    } else if (existingContact.getId().contentEquals(contact.getId())) {
      // Existe el email pero es un contacto con el mismo id
      return true;
    } else {
      // Hay coincidencia en dos ids distintos
      return false;
    }

  }


  public Contacto createContacto(Contacto contact) {
    Contacto newContact = null;
    if (!hasEmptyFields(contact) && isValidMail(contact)) {
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

    if (!hasEmptyFields(contact) && isValidMail(contact)) {

      newContact = DAO.updateContact(contact);
    }
    if (newContact != null) {
      return newContact;
    } else {
      contact.setId("");
      return contact;
    }
  }

  public String getContactsTotal() throws ServiceException {
    List<Contacto> contactList = DAO.getAllContacts();
    if (contactList != null) {
      return String.valueOf(contactList.size());
    } else {
      throw new ServiceException("No se pudo acceder a la bd", Status.NOT_FOUND);
    }
  }

  public boolean validateEmail(Contacto contact) {
    String regex =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    return Pattern.compile(regex).matcher(contact.getEmail()).matches();
  }

}
