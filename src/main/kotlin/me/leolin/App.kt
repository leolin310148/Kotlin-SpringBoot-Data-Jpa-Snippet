package me.leolin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity

/**
 * @author leolin
 */
fun main(args: Array<String>) {
    SpringApplicationBuilder()
            .sources(javaClass<App>())
            .run()
}

SpringBootApplication open class App

//workaround for JpaRepository<T,ID> which ID should be Serializable
Embeddable class TaskId(val id: Int) : Serializable {constructor() : this(0) }

Entity data class Task(
        EmbeddedId val id: TaskId,
        Column val name: String
) : Serializable {constructor() : this(TaskId(), "") }

interface TaskRepository : JpaRepository<Task, TaskId>

Service class TaskService
Autowired constructor(
        val taskRepository: TaskRepository
) {
    fun getAllTask() = taskRepository.findAll()
}

RestController class TaskRestController
Autowired constructor(
        val taskService: TaskTransactionalService
) {
    RequestMapping(value = "/tasks") fun getAllTask() = taskService.getAllTask()
}

