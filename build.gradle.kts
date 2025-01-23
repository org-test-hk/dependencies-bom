plugins {
    id("maven-publish")
    id("java-platform")
}

group = "kr.or.komca"
version = "0.0.1-SNAPSHOT"


javaPlatform {
    allowDependencies()
}



dependencies {
    constraints {
        api("kr.or.komca:komca-data-core:0.2.5")
        api("kr.or.komca:utils:0.3.2")
        api("kr.or.komca.foundation:verification:0.5.10")
        api("kr.or.komca:common-logging:0.1.0")
        api("kr.or.komca:exception:0.0.1")
    }
}



publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY")}")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("bom") {
            from(components["javaPlatform"])
            groupId = "kr.or.komca"
            artifactId = "dependencies-bom"
        }
    }
}

