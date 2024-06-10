plugins {
    alias(libs.plugins.android101.jvm.library)
}

dependencies {
    api(libs.store)
    api(libs.quiver)

    implementation(libs.eithernet)
}
