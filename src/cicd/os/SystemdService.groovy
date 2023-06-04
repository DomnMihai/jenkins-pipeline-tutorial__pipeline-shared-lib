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

}
