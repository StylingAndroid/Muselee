plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.detekt) version (BuildPlugins.Versions.detekt)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.stylingandroid.muselee"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    lintOptions {
        disable("IconLauncherShape")
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(ProjectModules.core))
    implementation(project(ProjectModules.topartists))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.glide)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidCompiler)
    kapt(Libraries.glideCompiler)

    testImplementation(TestLibraries.junit4)
}

detekt {
    version = BuildPlugins.Versions.detekt
    input = files("src/main/java", "src/androidx/java", "src/support/java")
    filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
}
