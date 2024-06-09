import com.android101.buildlogic.configureAndroidLint
import com.android101.buildlogic.configureDetekt
import com.android101.buildlogic.configureHilt
import com.android101.buildlogic.configureKotlin
import com.android101.buildlogic.configureMoshi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            configureDetekt()
            configureAndroidLint()
            configureKotlin()

            extensions.create("android101", JvmLibrary::class)
        }
    }
}

abstract class JvmLibrary(
    private val project: Project,
) {
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
