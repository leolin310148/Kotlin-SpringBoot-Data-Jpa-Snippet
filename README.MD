Kotlin-SpringBoot-Data-Jpa-Snippet
================

A simple example for Kotlin with SpringBoot and Data-JPA

Spring Data JPA
---
make data class work with JPA Entity

    //workaround for JpaRepository<T,ID> which ID should be Serializable
    Embeddable class TaskId(val id: Int) : Serializable {constructor() : this(0) }
    
    Entity data class Task(
            EmbeddedId val id: TaskId,
            Column val name: String
    ) : Serializable {constructor() : this(TaskId(), "") }
    
    
Transactional
---
Transactional will not work with kotlin, so I have to wrap TaskService with Java class "TaskTransactionalService".
     
     @Transactional
     @Service
     public class TaskTransactionalService {
         @Autowired
         private TaskService taskService;
     
         public List<Task> getAllTask() {
             return taskService.getAllTask();
         }
     }
