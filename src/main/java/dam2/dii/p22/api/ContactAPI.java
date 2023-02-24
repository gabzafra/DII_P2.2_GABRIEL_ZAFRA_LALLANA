package dam2.dii.p22.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dam2.dii.p22.service.ContactoService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("contact")
public class ContactAPI {
  private ContactoService contactSrv = new ContactoService();

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getContactsList() {
    return "Lista";
  }

  @GET
  @Path("size")
  @Produces(MediaType.TEXT_PLAIN)
  public String getContactsTotal() {
    return contactSrv.getContactsTotal();
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public String createNewContact() {
    return "create new";
  }

  @PUT
  @Produces(MediaType.TEXT_PLAIN)
  public String updateContact() {
    return "update contact";
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public String deleteContactById() {
    return "delete contact";
  }
}
