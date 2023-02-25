package dam2.dii.p22.service;

import javax.ws.rs.core.Response.Status;

public class ServiceException extends Exception {
  private static final long serialVersionUID = 1L;
  private Status status;

  public ServiceException(String message, Status status) {
    super(message);
    this.status = status;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }


}
