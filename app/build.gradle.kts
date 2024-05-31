plugins {
    alias(libs.plugins.android101.application)
}

android101 {
    compose()
}

android {
    namespace = "com.android101"

    defaultConfig {
        applicationId = "com.android101"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(projects.ui.theme)
}