plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
    signing
}

android {
    namespace = "io.github.rustui.resvg"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("release")
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.rustui"
            artifactId = "resvg"
            version = "0.2"

            pom {
                name = "resvg"
                description = "An SVG rendering library."
                url = "https://github.com/rustui/resvg_module"
                developers {
                    developer {
                        id = "rustui"
                        name = "rustui"
                        email = "90625190+rustui@users.noreply.github.com"
                    }
                }
                licenses {
                    license {
                        name = "MIT license"
                        url = "https://github.com/rustui/resvg_module/blob/main/LICENSE"
                    }
                }
                scm {
                    url = "https://github.com/rustui/resvg_module.git"
                    connection = "scm:git:git://github.com/rustui/resvg_module.git"
                    developerConnection = "scm:git:ssh://github.com/rustui/resvg_module.git"
                }
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("resvg"))
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["release"])
}