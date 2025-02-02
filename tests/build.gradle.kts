dependencies {
    testImplementation(project(":at-api-client"))
    testImplementation(project(":at-web-integration"))
    testImplementation(project(":at-web-selenium"))
    testImplementation(project(":common"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")

    testImplementation("name.falgout.jeffrey.testing.junit5:guice-extension")
    testCompileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
