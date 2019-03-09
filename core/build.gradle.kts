import io.gitlab.arturbosch.detekt.detekt

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.detekt) version (BuildPlugins.Versions.detekt)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    api(Libraries.okHttp)
    api(Libraries.loggingInterceptor)
    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidCompiler)
    api(Libraries.workManager)

    testImplementation(TestLibraries.junit4)
}

detekt {
    version = BuildPlugins.Versions.detekt
    input = files("src/main/java", "src/androidx/java", "src/support/java")
    filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
}
