import cicd.os.SystemdService;

public void call(Map attrs = [:]) {
  // Get attributes
  final String command = attrs["command"];
  final String serviceName = attrs["serviceName"];

  // Create SystemdService object
  final SystemdService systemdService = new SystemdService(serviceName);

  // Interact with systemd
  switch (command) {
    case "stop":
      systemdService.stopService();
      sleep(time: 5, unit: "SECONDS");
      _assertServiceStatus(systemdService, "stopped");
      break;
    default:
      error("systemd command '${command}' not supported");
  }
}

private void _assertServiceStatus(SystemdService systemdService, String expectedStatus) {
  switch (expectedStatus) {
    case "stopped":
      if (systemdService.isServiceActive()) {
        error("Systemd service '${systemdService.SERVICE_NAME}' is not stopped");
      } else {
        echo("Systemd service '${systemdService.SERVICE_NAME}' is stopped");
      }
      break;
    default:
      error("Expected status '${expectedStatus}' not supported");
  }
}
