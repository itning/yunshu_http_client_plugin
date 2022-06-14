package com.github.itning.yunshuhttpclientplugin.services

import com.intellij.openapi.project.Project
import com.github.itning.yunshuhttpclientplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
