plugins {
    alias(libs.plugins.android101.android.library)
    alias(libs.plugins.google.secrets)
}

android {
    namespace = "com.android101.backend.spotify"
    buildFeatures {
        buildConfig = true
    }
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.defaults.properties"
}

android101 {
    hilt()
    moshi(codegen = true)
}

dependencies {
    api(libs.eithernet)
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit)
}
