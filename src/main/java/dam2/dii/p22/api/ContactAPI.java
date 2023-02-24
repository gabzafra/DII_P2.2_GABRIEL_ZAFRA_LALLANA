package dam2.dii.p22.api;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dam2.dii.p22.model.Contacto;
import dam2.dii.p22.service.ContactoService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("contact")
public class ContactAPI {
  private ContactoService contactSrv = new ContactoService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<Contacto> getContactsList() {
    return (ArrayList<Contacto>) contactSrv.getAllContacts();
  }

  @GET
  @Path("size")
  @Produces(MediaType.TEXT_PLAIN)
  public String getContactsTotal() {
    return contactSrv.getContactsTotal();
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.TEXT_PLAIN)
  public String createNewContact(@FormParam("name") String name,
      @FormParam("surnames") String surnames, @FormParam("email") String email,
      @FormParam("phone") String phone, @FormParam("coments") String coments) {

    Contacto c = new Contacto();
    c.setName(name);
    c.setSurnames(surnames);
    c.setEmail(email);
    c.setPhone(phone);
    c.setComents(coments);

    Contacto result = contactSrv.createContacto(c);
    if (result.getId().equals("")) {
      return "Se ha producido un error al intentar crear el contacto";
    } else {
      return result.toString();
    }
  }

  @PUT
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.TEXT_PLAIN)
  public String updateContact(@FormParam("id") String id, @FormParam("name") String name,
      @FormParam("surnames") String surnames, @FormParam("email") String email,
      @FormParam("phone") String phone, @FormParam("coments") String coments) {

    Contacto c = new Contacto();
    c.setId(id);
    c.setName(name);
    c.setSurnames(surnames);
    c.setEmail(email);
    c.setPhone(phone);
    c.setComents(coments);

    Contacto result = contactSrv.updateContacto(c);
    if (result.getId().equals("")) {
      return "Se ha producido un error al intentar actualizar el contacto";
    } else {
      return result.toString();
    }
  }

  @DELETE
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String deleteContactById(@PathParam("id") String contactId) {
    if (contactSrv.deleteContactoById(contactId)) {
      return "Se ha eliminado el contacto con id:" + contactId;
    } else {
      return "Se ha producido un error al intentar actualizar el contacto";
    }
  }
}
