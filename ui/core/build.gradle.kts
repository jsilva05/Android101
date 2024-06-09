plugins {
    alias(libs.plugins.android101.android.library)
}

android {
    namespace = "com.android101.ui.core"
}

android101 {
    compose()
}

dependencies {
    api(libs.molecule)
}
