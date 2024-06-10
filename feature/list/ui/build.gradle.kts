plugins {
    alias(libs.plugins.android101.android.library)
}

android {
    namespace = "com.android101.feature.list.ui"
}

android101 {
    compose()
    hilt()
}

dependencies {
    implementation(projects.feature.list.api)

    implementation(projects.ui.core)
    implementation(projects.ui.shared)
}
