package xarmanta.mainwindow.infraestructure.services
import  javafx.concurrent.Service;
import  javafx.concurrent.Task;
import xarmanta.mainwindow.infraestructure.XarmantProgressMonitor
import xarmanta.mainwindow.shared.GitContext
import xarmanta.mainwindow.shared.XGit
import java.io.File

class OpenRepoService private constructor(
    val dir: File?,
    val monitor: XarmantProgressMonitor?
    ):Service<XGit>() {
    override fun createTask(): Task<XGit> {
        return object : Task<XGit>() {
            override fun call(): XGit {
                return XGit(GitContext(null, dir)!!, monitor!!).open()
            }
        }
    }

    data class Builder(var dir: File? = null,
                       var monitor: XarmantProgressMonitor? = null) {

        fun dir(dir: File) = apply { this.dir = dir }
        fun monitor(monitor: XarmantProgressMonitor) = apply { this.monitor = monitor }
        fun build() = OpenRepoService(dir,monitor)
    }
}