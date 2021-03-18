import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")

}

kotlin {
    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                useExperimentalAnnotation("kotlinx.serialization.InternalSerializationApi")
                useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            }
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

kotlin {

    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
        implementation("com.autodesk:coroutineworker:0.6.3")
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("stdlib-common"))
        implementation("org.jetbrains.kotlin:kotlin-test-multiplatform")
        implementation("app.cash.turbine:turbine:0.2.1")
    }

    jvm()
    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib", "1.4.20"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
    }
    sourceSets["jvmTest"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2-native-mt")
    }

    ios()
    watchos()
    tvos()

    linuxX64()
    macosX64("macos")
    mingwX64()


    sourceSets.create("nativeTest")
    sourceSets.create("nativeMain")

    configure(
        listOf(
            targets["iosArm64"],
            targets["iosX64"],

            targets["watchosArm32"],
            targets["watchosArm64"],
            targets["watchosX86"],

            targets["tvosArm64"],
            targets["tvosX64"],

            targets["linuxX64"],
            targets["macos"],
            targets["mingwX64"]
        )
    ) {
        compilations["main"].source(sourceSets["nativeMain"])
        compilations["test"].source(sourceSets["nativeTest"])
    }

    sourceSets {
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }

    cocoapodsext {
        summary = "shared $name module"
        framework {
            transitiveExport = true
            homepage = "$name home"
            setVersion("1.0")
        }
    }
}