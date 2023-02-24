package dam2.dii.p22.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import dam2.dii.p22.config.Config;
import dam2.dii.p22.model.Contacto;

public class ContactDAOXml implements ContactDAO {

  private static ContactDAOXml instance;
  private static Config envConfig = Config.getConfig();

  private static final File CONTACT_FILE = new File(
      envConfig.getGlobalPath() + File.separator + "WEB-INF" + File.separator + "contactos.xml");

  private ContactDAOXml() {
    if (getAllContacts().isEmpty()) {
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
  }

  public static ContactDAOXml getInstance() {
    if (instance == null) {
      instance = new ContactDAOXml();
    }
    return instance;
  }

  @Override
  public List<Contacto> getAllContacts() {
    List<Contacto> response = new ArrayList<Contacto>();
    Document doc = readXMLFile();
    if (doc.hasRootElement()) {
      response = (List<Contacto>) doc.getRootElement().getChildren().stream()
          .map(ContactDAOXml::unSerialContact).collect(Collectors.toList());
    }
    return response;
  }

  @Override
  public Contacto getContactById(String id) {
    return getAllContacts().stream().filter(item -> item.getId().equals(id)).findFirst()
        .orElse(new Contacto());
  }

  @Override
  public Contacto createContact(Contacto contact) {
    List<Contacto> contactList = getAllContacts();

    Integer newId;
    if (!contactList.isEmpty()) {
      newId = contactList.stream().map(item -> Integer.parseInt(item.getId())).max(Integer::compare)
          .get() + 1;
    } else {
      newId = 1;
    }


    Contacto newContact = new Contacto();

    newContact.setId(String.valueOf(newId));
    newContact.setName(contact.getName());
    newContact.setSurnames(contact.getSurnames());
    newContact.setEmail(contact.getEmail());
    newContact.setPhone(contact.getPhone());
    newContact.setComents(contact.getComents());

    contactList.add(newContact);

    Document newDoc = serializeContactList(contactList);

    if (!writeXMLFile(newDoc).hasRootElement()) {
      return new Contacto();
    } else {
      return getContactById(newContact.getId());
    }
  }

  @Override
  public boolean deleteContactById(String id) {
    List<Contacto> contactList = getAllContacts();
    int initialSize = contactList.size();
    contactList = contactList.stream().filter(contact -> !contact.getId().equals(id))
        .collect(Collectors.toList());

    Document newDoc = serializeContactList(contactList);

    if (!writeXMLFile(newDoc).hasRootElement()) {
      return false;
    } else {
      return getAllContacts().size() < initialSize;
    }
  };

  @Override
  public Contacto updateContact(Contacto contact) {
    List<Contacto> contactList = getAllContacts();
    contactList = contactList.stream().map(
        currentContact -> currentContact.getId().equals(contact.getId()) ? contact : currentContact)
        .collect(Collectors.toList());

    Document newDoc = serializeContactList(contactList);

    if (!writeXMLFile(newDoc).hasRootElement()) {
      return contact;
    } else {
      return getContactById(contact.getId());
    }
  }

  // XML Handling

  private Document readXMLFile() {
    SAXBuilder builder = new SAXBuilder();
    Document doc;
    try {
      doc = builder.build(CONTACT_FILE);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }

  private static Document writeXMLFile(Document doc) {
    Format format = Format.getPrettyFormat();
    XMLOutputter xmlOut = new XMLOutputter(format);
    String nuevoDoc = xmlOut.outputString(doc);

    try (PrintWriter writer = new PrintWriter(new FileWriter(CONTACT_FILE))) {
      writer.println(nuevoDoc);
    } catch (Exception e) {
      return new Document();
    }
    return doc;
  }


  private static Contacto unSerialContact(Element node) {
    Contacto newContact = new Contacto();
    newContact.setId(node.getAttributeValue("id"));
    newContact.setName(node.getChild("name").getText());
    newContact.setSurnames(node.getChild("surnames").getText());
    newContact.setEmail(node.getChild("email").getText());
    newContact.setPhone(node.getChild("phone").getText());
    newContact.setComents(node.getChild("comments").getText());
    return newContact;
  }

  private static Document serializeContactList(List<Contacto> contactList) {
    Document newDoc = new Document();

    Element rootNode = new Element("contacts");
    newDoc.addContent(rootNode);

    for (Contacto qData : contactList) {
      Element contactNode = new Element("contact").setAttribute("id", qData.getId());
      Element nameNode = new Element("name").setText(qData.getName());
      Element surnameNode = new Element("surnames").setText(qData.getSurnames());
      Element emailNode = new Element("email").setText(qData.getEmail());
      Element phoneNode = new Element("phone").setText(qData.getPhone());
      Element commentNode = new Element("comments").setText(qData.getComents());


      contactNode.addContent(nameNode);
      contactNode.addContent(surnameNode);
      contactNode.addContent(emailNode);
      contactNode.addContent(phoneNode);
      contactNode.addContent(commentNode);

      rootNode.addContent(contactNode);
    }

    return newDoc;
  }
}
