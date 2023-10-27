plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-base`
}
task<Exec>("test") {
    commandLine("./run-tests.sh", "")
}