public void call(String additionalMessage = "") {
  sh(script: 'date', label: 'Print server time');
  echo("additional message: " + additionalMessage);
}
