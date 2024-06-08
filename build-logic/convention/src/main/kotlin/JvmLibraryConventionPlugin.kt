import com.android101.buildlogic.configureAndroidLint
import com.android101.buildlogic.configureDetekt
import com.android101.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            configureDetekt()
            configureAndroidLint()
            configureKotlin()
        }
    }
}
