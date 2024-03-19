package org.maxym.spring.service;

import lombok.RequiredArgsConstructor;
import org.maxym.spring.domain.Task;
import org.maxym.spring.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findAll(Integer pageNumber, Integer pageSize) {
        return taskRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
