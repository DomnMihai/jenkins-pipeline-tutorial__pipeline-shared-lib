package cicd.os

import cicd.config.DeploymentConfigs;

class SystemdService {

  public final String SERVICE_NAME;

  public SystemdService(String serviceName) {
    this.SERVICE_NAME = serviceName;
  }

  public void stopService() {
    DeploymentConfigs.jobInstance.sh(script: "sudo systemctl stop ${SERVICE_NAME}", label: "Stop systemd service");
  }

  public boolean isServiceActive() {
    final String serviceActiveString = DeploymentConfigs.jobInstance.sh(script: "systemctl is-active ${SERVICE_NAME} || true", returnStdout: true, label: "Is systemd service active");
    DeploymentConfigs.jobInstance.echo(serviceActiveString);
    if (serviceActiveString.trim() == "active") {
      return true;
    }

    return false;
  }
}
