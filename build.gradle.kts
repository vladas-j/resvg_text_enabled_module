plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
    signing
}

android {
    namespace = "io.github.vladas-j.resvg"
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
            groupId = "io.github.vladas-j"
            artifactId = "resvg"
            version = "0.3"

            pom {
                name = "resvg"
                description = "An SVG rendering library."
                url = "https://github.com/vladas-j/resvg_text_enabled_module"
                developers {
                    developer {
                        id = "vladas-j"
                        name = "vladas-j"
                        email = "90625190+vladas-j@users.noreply.github.com"
                    }
                }
                licenses {
                    license {
                        name = "MIT license"
                        url = "https://github.com/vladas-j/resvg_text_enabled_module.git/blob/main/LICENSE"
                    }
                }
                scm {
                    url = "https://github.com/vladas-j/resvg_text_enabled_module.git"
                    connection = "scm:git:git://github.com/vladas-j/resvg_text_enabled_module.git"
                    developerConnection = "scm:git:ssh://github.com/vladas-j/resvg_text_enabled_module.git"
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