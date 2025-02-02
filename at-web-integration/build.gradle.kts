dependencies {
    implementation(project(":common"))

    implementation("com.microsoft.playwright:playwright")

    implementation("com.google.inject:guice")
    implementation("org.aeonbits.owner:owner")
    implementation("org.junit.jupiter:junit-jupiter-api")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

