package dam2.dii.p22.model;

public class Contacto {

  private String id;
  private String name;
  private String surnames;
  private String email;
  private String phone;
  private String coments;

  public Contacto() {}

  public Contacto(String name, String surnames, String email, String phone, String coments) {
    this(null, name, surnames, email, phone, coments);
  }

  public Contacto(String id, String name, String surnames, String email, String phone,
      String coments) {
    this.id = id;
    this.name = name;
    this.surnames = surnames;
    this.email = email;
    this.phone = phone;
    this.coments = coments;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurnames() {
    return surnames;
  }

  public void setSurnames(String surnames) {
    this.surnames = surnames;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getComents() {
    return coments;
  }

  public void setComents(String coments) {
    this.coments = coments;
  }

}
