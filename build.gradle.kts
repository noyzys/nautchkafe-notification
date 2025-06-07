plugins {
    id("java")
    kotlin("jvm") version "2.1.10"
}

group = "dev.nautchkafe.fmap.notification"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")

    maven("https://repo.spongepowered.org/repository/maven-releases/")
    maven("https://repo.velocitypowered.com/repository/maven-releases/")
}

dependencies {
    // fp stuff
    implementation("io.vavr:vavr:0.10.5")

    // minecraft server-side software stuff
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.20.1-experimental-SNAPSHOT")
    compileOnly("org.spongepowered:spongeapi:13.0.0") // Sponge API
    implementation("com.github.Minestom:Minestom:1.4.0")

    /* minecraft proxy side software */
    compileOnly("com.velocitypowered:velocity-api:3.1.1")
    compileOnly("net.md-5:bungeeapi:1.20")

    // kyori stuff
    implementation("net.kyori.adventure:adventure-api:4.11.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
    implementation("net.kyori.adventure:adventure-text-minimessage:4.11.0")

    // tests stuff
    testImplementation(platform("org.junit:junit-bom:5.13.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
