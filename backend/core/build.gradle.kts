plugins {
    alias(libs.plugins.android101.jvm.library)
}

android101 {
    hilt()
    moshi()
}

dependencies {
    implementation(libs.eithernet.retrofit)
    implementation(platform(libs.retrofit.bom))
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
}
