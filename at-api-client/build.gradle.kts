dependencies {
    implementation(project(":common"))

    implementation("com.google.inject:guice")
    implementation("org.aeonbits.owner:owner")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("com.squareup.retrofit2:retrofit")
    implementation("com.squareup.retrofit2:converter-jackson")

    implementation("org.slf4j:slf4j-api")
    implementation("org.slf4j:slf4j-simple")

    implementation("io.qameta.allure:allure-okhttp3")
    implementation("org.apache.commons:commons-text")
}

