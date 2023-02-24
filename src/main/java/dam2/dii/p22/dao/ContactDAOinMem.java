package dam2.dii.p22.dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import dam2.dii.p22.model.Contacto;

public class ContactDAOinMem implements ContactDAO {
  private static ContactDAOinMem instance;
  private HashMap<String, Contacto> contactList;
  private int id;

  private ContactDAOinMem() {
    contactList = new HashMap<String, Contacto>();
    id = 0;

    createContact(new Contacto("Adam", "Alda Almirante", "adam@mail.com", "666554433",
        "Lorem ipsum dolor sit amet consectetur adipiscing elit, enim lectus sed cubilia primis donec in ultrices, sociis himenaeos aptent cum proin tellus."));
    createContact(new Contacto("Betty", "Bueno Baños", "betty@mail.com", "555443322",
        "Aptent luctus turpis sollicitudin torquent nunc morbi vel ac class, rutrum metus leo sodales erat cursus ut interdum augue, mattis proin eu risus nullam pulvinar magnis consequat."));
    createContact(new Contacto("Charlie", "Corral Casares", "charlie@mail.com", "444332211",
        "Pharetra fermentum praesent platea phasellus ornare arcu, vehicula nisl velit cras natoque dis tellus, donec penatibus senectus potenti massa."));
    createContact(new Contacto("Diane", "Dueñas Donoso", "diane@mail.com", "333221100",
        "Torquent congue interdum integer felis nibh a imperdiet posuere, massa non vehicula blandit magna pellentesque accumsan mattis fermentum, orci senectus porta natoque aliquam odio turpis."));
    createContact(new Contacto("Eric", "Estepa España", "eric@mail.com", "111223344",
        "Nullam platea mattis fermentum condimentum proin id ornare felis taciti, interdum laoreet aenean fringilla turpis varius aptent tellus cursus malesuada, nisi ridiculus pharetra fusce pulvinar habitant congue ultrices."));
  }

  public static ContactDAOinMem getInstance() {
    if (instance == null) {
      instance = new ContactDAOinMem();
    }
    return instance;
  }

  private String getNewId() {
    id++;
    return String.valueOf(id);
  }

  private static Contacto cloneContact(Contacto c) {
    return new Contacto(c.getId(), c.getName(), c.getSurnames(), c.getEmail(), c.getPhone(),
        c.getComents());
  }

  @Override
  public Contacto createContact(Contacto contact) {
    contact.setId(getNewId());
    if (!contactList.containsKey(contact.getId())) {
      contactList.put(contact.getId(), contact);
      return cloneContact(contactList.get(contact.getId()));
    } else {
      return null;
    }
  }

  @Override
  public List<Contacto> getAllContacts() {
    return contactList.values().stream().map(ContactDAOinMem::cloneContact)
        .collect(Collectors.toList());
  }

  @Override
  public Contacto getContactById(String id) {
    Contacto foundContact = contactList.get(id);
    return foundContact != null ? cloneContact(foundContact) : null;
  }

  @Override
  public boolean deleteContactById(String id) {
    return contactList.remove(id) != null;
  }

  @Override
  public Contacto updateContact(Contacto contact) {
    contactList.replace(contact.getId(), contact);
    return getContactById(contact.getId());
  }
}
