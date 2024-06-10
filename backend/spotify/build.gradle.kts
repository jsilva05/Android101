plugins {
    alias(libs.plugins.android101.jvm.library)
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
