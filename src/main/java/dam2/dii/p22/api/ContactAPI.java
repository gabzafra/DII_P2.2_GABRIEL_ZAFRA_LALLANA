package dam2.dii.p22.api;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import dam2.dii.p22.model.Contacto;
import dam2.dii.p22.service.ContactoService;
import dam2.dii.p22.service.ServiceException;

@Path("contact")
public class ContactAPI {
  private ContactoService contactSrv = new ContactoService();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getContactsList() {
    try {
      List<Contacto> contactList = contactSrv.getAllContacts();
      return Response.ok(contactList).build();
    } catch (ServiceException e) {
      return Response.status(e.getStatus()).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("size")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getContactsTotal() {
    try {
      String total = contactSrv.getContactsTotal();
      return Response.ok(total).build();
    } catch (ServiceException e) {
      return Response.status(e.getStatus()).entity(e.getMessage()).build();
    }
  }

  @POST
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.TEXT_PLAIN)
  public Response createNewContact(@DefaultValue("") @FormParam("name") String name,
      @DefaultValue("") @FormParam("surnames") String surnames,
      @DefaultValue("") @FormParam("email") String email,
      @DefaultValue("") @FormParam("phone") String phone,
      @DefaultValue("") @FormParam("coments") String coments) {
    Contacto newContactData = new Contacto();
    newContactData.setName(name);
    newContactData.setSurnames(surnames);
    newContactData.setEmail(email);
    newContactData.setPhone(phone);
    newContactData.setComents(coments);

    try {
      Contacto result = contactSrv.createContacto(newContactData);
      return Response.ok(result.toString()).build();
    } catch (ServiceException e) {
      return Response.status(e.getStatus()).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Consumes("application/x-www-form-urlencoded")
  @Produces(MediaType.TEXT_PLAIN)
  public Response updateContact(@DefaultValue("") @FormParam("id") String id,
      @DefaultValue("") @FormParam("name") String name,
      @DefaultValue("") @FormParam("surnames") String surnames,
      @DefaultValue("") @FormParam("email") String email,
      @DefaultValue("") @FormParam("phone") String phone,
      @DefaultValue("") @FormParam("coments") String coments) {

    Contacto updContactData = new Contacto();
    updContactData.setId(id);
    updContactData.setName(name);
    updContactData.setSurnames(surnames);
    updContactData.setEmail(email);
    updContactData.setPhone(phone);
    updContactData.setComents(coments);

    try {
      Contacto result = contactSrv.updateContacto(updContactData);
      return Response.ok(result.toString()).build();
    } catch (ServiceException e) {
      return Response.status(e.getStatus()).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public Response deleteContactById(@PathParam("id") String contactId) {
    try {
      contactSrv.deleteContactoById(contactId);
      return Response.status(Status.NO_CONTENT).build();
    } catch (ServiceException e) {
      return Response.status(e.getStatus()).entity(e.getMessage()).build();
    }
  }
}
