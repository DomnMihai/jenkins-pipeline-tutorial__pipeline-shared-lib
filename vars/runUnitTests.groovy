public void call() {
  sh(script: "npm run test-cicd", label: "Run node.js unit tests");
}
