public void call() {
  _npmInstall();
}

private void _npmInstall() {
  // If npm ci doesn't work, use npm install
  sh(script: "npm ci", label: "Install npm dependencies");
}
