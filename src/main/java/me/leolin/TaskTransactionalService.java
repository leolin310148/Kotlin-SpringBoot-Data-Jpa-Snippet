package me.leolin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author leolin
 *         WorkAround for @Transactional will not work with kotlin
 */
@Transactional
@Service
public class TaskTransactionalService {
    @Autowired
    private TaskService taskService;

    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }
}
