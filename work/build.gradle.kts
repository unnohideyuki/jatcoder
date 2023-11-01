task<Exec>("test") {
    commandLine("./run-tests.sh", "")
}
task("check") {
    dependsOn("test")
}