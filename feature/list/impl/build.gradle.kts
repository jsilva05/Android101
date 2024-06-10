plugins {
    alias(libs.plugins.android101.android.library)
}

android {
    namespace = "com.android101.feature.list"
}

android101 {
    hilt()
}

dependencies {
    implementation(projects.backend.spotify)
    implementation(projects.core.impl)
    implementation(projects.feature.list.api)
    implementation(projects.feature.list.db)
}
