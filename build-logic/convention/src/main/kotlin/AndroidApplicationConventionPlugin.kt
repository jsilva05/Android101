import com.android.build.api.dsl.ApplicationExtension
import com.android101.buildlogic.AndroidVersions.TargetSdk
import com.android101.buildlogic.configureAndroid
import com.android101.buildlogic.configureCompose
import com.android101.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)
                defaultConfig.targetSdk = TargetSdk
            }

            configureKotlin()

            extensions.create("android101", AndroidApplication::class)
        }
    }
}

abstract class AndroidApplication(
    private val project: Project,
) {
    /** Enable Jetpack Compose on this module, and add libraries. */
    fun compose() = with(project) {
        extensions.configure<ApplicationExtension> {
            configureCompose(this)
        }
    }
}