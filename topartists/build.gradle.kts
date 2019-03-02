
import java.io.FileInputStream
import java.util.Properties

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.detekt) version (BuildPlugins.Versions.detekt)
}

val props = Properties()
val localProperties: File = project.rootProject.file("local.properties")
if (localProperties.exists()) {
    props.load(FileInputStream(localProperties))
}
if (!props.containsKey("last.fm.apikey")){
    props["last.fm.apikey"] = rootProject.properties["LAST_FM_APIKEY"]
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "LAST_FM_APIKEY", props["last.fm.apikey"] as String)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(project(ProjectModules.core))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.archLifecycle)
    api(Libraries.archRoomRuntime)
    kapt(Libraries.archRoomCompiler)
    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.glide)
    api(Libraries.retrofit)
    implementation(Libraries.retrofitMoshi)
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
