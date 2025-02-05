plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(
            "io.rest-assured:rest-assured:5.5.0"
    )
    implementation(
            "org.junit.jupiter:junit-jupiter:5.11.4"
    )
    implementation(
            "io.rest-assured:json-schema-validator:5.5.0"
    )

    implementation(
           "org.junit.jupiter:junit-jupiter-params:5.11.4"

    )
}



tasks.test {
    useJUnitPlatform()
}