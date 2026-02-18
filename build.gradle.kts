
plugins {
    kotlin("jvm") version "1.9.22"
    application
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("HelloCV")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repository.hellonico.info/repository/hellonico/")
    maven("https://clojars.org/repo/")
}

dependencies {
    implementation("origami:origami:4.13.0-2-SNAPSHOT")
    implementation("org.clojure:clojure:1.11.3")
    implementation("org.slf4j:slf4j-nop:1.7.36")
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
    }
}
