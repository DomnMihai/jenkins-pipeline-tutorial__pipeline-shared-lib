public void call(boolean onlyProductionDependencies = false) {
  _npmInstall(onlyProductionDependencies);
}

private void _npmInstall(boolean onlyProductionDependencies) {
  final String omitDevFlag = onlyProductionDependencies ? "--omit=dev" : "";

  // If npm ci doesn't work, use npm install
  sh(script: "npm ci ${omitDevFlag}", label: "Install npm dependencies");
}
