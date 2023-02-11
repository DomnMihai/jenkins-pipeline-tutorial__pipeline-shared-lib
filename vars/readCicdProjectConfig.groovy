import cicd.config.DeploymentConfigs;

public Map call() {
  final Map cicdConfigMap = DeploymentConfigs.globalConfigs["cicdConfig"];
  final String pipelineConfigFolderPath = cicdConfigMap["pipelineConfigFolderPath"];
  final GString pipelineConfigYamlPath = "${pipelineConfigFolderPath}/pipeline-config.yml";

  // Read config
  final Map pipelineConfigMap = readYaml(file: pipelineConfigYamlPath);
  return pipelineConfigMap;
}
