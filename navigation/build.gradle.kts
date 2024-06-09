plugins {
    alias(libs.plugins.android101.android.library)
}

android {
    namespace = "com.android101.navigation"
}

android101 {
    compose()
}

dependencies {
    implementation(projects.feature.list.ui)

    implementation(libs.androidx.compose.navigation)
}
