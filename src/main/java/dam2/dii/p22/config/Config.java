package dam2.dii.p22.config;

public final class Config {
  private static Config instance;
  private String globalPath;

  private Config() {}

  public static Config getConfig() {
    if (instance == null) {
      instance = new Config();
    }
    return instance;
  }

  public void setGlobalPath(String path) {
    globalPath = path;
  }

  public String getGlobalPath() {
    return globalPath;
  }
}
