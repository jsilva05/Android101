import com.android.build.api.dsl.LibraryExtension
import com.android101.buildlogic.configureAndroid
import com.android101.buildlogic.configureAndroidLint
import com.android101.buildlogic.configureCompose
import com.android101.buildlogic.configureDetekt
import com.android101.buildlogic.configureHilt
import com.android101.buildlogic.configureKotlin
import com.android101.buildlogic.configureLogger
import com.android101.buildlogic.configureMoshi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid(this)
            }

            configureDetekt()
            configureAndroidLint()
            configureKotlin()
            configureLogger()

            extensions.create("android101", AndroidLibrary::class)
        }
    }
}


abstract class AndroidLibrary(
    private val project: Project,
) {
    /** Enable Jetpack Compose on this module, and add libraries. */
    fun compose() = with(project) {
        extensions.configure<LibraryExtension> {
            configureCompose(this)
        }
    }

    /** Enable Hilt on this module, and add libraries. */
    fun hilt() = with(project) {
        configureHilt()
    }

    /** Enable Moshi on this module, and add libraries. */
    fun moshi(
        codegen: Boolean = false,
        testsCodegen: Boolean = false,
    ) = with(project) {
        configureMoshi(codegen, testsCodegen)
    }
}
