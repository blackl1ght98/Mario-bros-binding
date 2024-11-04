plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.fuentesbuenosvinosguillermo.mariobrosbinding"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fuentesbuenosvinosguillermo.mariobrosbinding"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    /**
     * Para tener el binding tenemos que poener:
     * buildFeatures{
     *         viewBinding=true
     *         dataBinding=true
     *
     * */
    buildFeatures{
        viewBinding=true
        dataBinding=true
    }
}

dependencies {
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}