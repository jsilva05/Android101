plugins {
    alias(libs.plugins.android101.jvm.library)
}

android101 {
    hilt()
    database("com.android101.feature.list.db")
}
