import cicd.config.DeploymentConfigs

public void call(String repositoryName, String sourceBranch) {
  // Fail if source branch is not provided
  if (!sourceBranch) {
    error("Source branch for git clone is '${sourceBranch}'");
  }

  // Clone repo
  final String gitUrl = _generateGitUrl(repositoryName);
  checkout(
    scm: [
      $class: "GitSCM",
      userRemoteConfigs: [[url: gitUrl]],
      branches: [[name: "refs/heads/${sourceBranch}"]],
      extensions: []
    ],
    changelog: true,
    poll: false
  );
}

private String _generateGitUrl(String repositoryName) {
  final String remoteUrlPattern = DeploymentConfigs.globalConfigs["git"]["remoteHttps"];
  return remoteUrlPattern.replace("<REPOSITORY_NAME>", repositoryName);
}
