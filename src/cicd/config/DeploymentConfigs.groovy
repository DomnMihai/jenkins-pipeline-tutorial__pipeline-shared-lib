package cicd.config

class DeploymentConfigs {

  public static def jobInstance = null;

  /**
   * Configuration from jenkins-lib <b>resources/global-configs.yaml</b>
   */
  public static Map globalConfigs = null;

  public static Map loadGlobalConfigs() {
    final String globalConfigsResource = DeploymentConfigs.jobInstance.libraryResource("global-configs.yaml");
    DeploymentConfigs.globalConfigs = DeploymentConfigs.jobInstance.readYaml(text: globalConfigsResource);
    DeploymentConfigs.jobInstance.echo("DEBUG: " + DeploymentConfigs.globalConfigs); // TODO Remove it after debug
    return DeploymentConfigs.globalConfigs;
  }
}
