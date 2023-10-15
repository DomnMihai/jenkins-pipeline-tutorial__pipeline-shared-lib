public void call(Map attrs = [:]) {
  // Get variables
  final String url = attrs["url"];
  final String expectedResponseCode = attrs["expectedResponseCode"];
  final Integer checksNumber = attrs["checksNumber"];
  final Integer checkDelay = attrs["checkDelay"];

  // Call server URL
  for (int ii = 0; ii < checksNumber; ii++) {
    sleep(time: checkDelay, unit: "SECONDS");
    final String httpCallStatusCode = sh(script: """curl --silent --output /dev/null --write-out "%{http_code}" --request GET ${url}""", label: "Check server health", returnStdout: true);
    echo("The request to '${url}' returned '${httpCallStatusCode}' status code");

    // Validate the status code
    assert httpCallStatusCode == expectedResponseCode : "Health check failed. Expected status code '${expectedResponseCode}', got '${httpCallStatusCode}'";
  }

}
