plugins {
    kotlin("js")
    id("maven-publish")
}

repositories()

kotlin {
    kotlinJsTargets()
}

dependencies {
    api(rootProject)
    implementation(npm("bootstrap-select", "^1.13.18"))
    implementation(npm("ajax-bootstrap-select", "^1.4.5"))
    testImplementation(kotlin("test-js"))
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(kotlin.sourceSets.main.get().kotlin)
}

publishing {
    publications {
        create<MavenPublication>("kotlin") {
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            pom {
                defaultPom()
            }
        }
    }
}

setupPublication()
