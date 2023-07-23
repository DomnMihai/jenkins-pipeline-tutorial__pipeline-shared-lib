public void call(Map attrs = [:]) {
  // Get attributes
  final String sourcePath = attrs["sourcePath"];
  final String backupDirectoryPath = attrs["backupDirectoryPath"];

  // Create backup
  if (_isDirectoryEmpty(sourcePath)) {
    echo("${sourcePath} is empty. Nothing to backup");

    // Generate empty mark
    final GString emptyMarkFileName = "no-backup-${env.p_PROJECT_NAME}-${env.BUILD_NUMBER}";
    final GString emptyMarkFilePath = "${backupDirectoryPath}/${emptyMarkFileName}";

    // Create mark
    sh(script: "touch ${emptyMarkFilePath}", label: "Create no-backup mark");
    echo("Created empty mark instead of backup, ${emptyMarkFilePath}");
  } else {
    final GString backupZipFileName = "backup-${env.p_PROJECT_NAME}-${env.BUILD_NUMBER}.zip";
    final GString backupZipFilePath = "${backupDirectoryPath}/${backupZipFileName}";

    sh(script: "zip -q -r -9 ${backupZipFilePath} ${sourcePath}", label: "Zip application");
    echo("Current application was backed up in ${backupZipFilePath}");
  }
}

private boolean _isDirectoryEmpty(String path) {
  // Check if directory exists
  if (!fileExists(path)) {
    return true;
  }

  // Check if directory is empty
  final String listFilesOutput = sh(script: "ls --almost-all ${path}", label: "List directory content", returnStdout: true);
  if (listFilesOutput) {
    return false;
  }

  return true;
}
