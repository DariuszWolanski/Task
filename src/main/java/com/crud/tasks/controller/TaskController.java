package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

//    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
//    public List<TaskDto> getTasks() {
//        List<Task> tasks = service.getAllTasks();
//        return taskMapper.mapToTaskDtoList(tasks);
//    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(tasks));
    }
//    @RequestMapping(method = RequestMethod.GET, value = "getTask")
//    public TaskDto getTask(Long taskId) {
//        return new TaskDto(1L, "test title", "test_content");
//    }

//    @GetMapping(value = {"taskId"})
//    public ResponseEntity getTask(@PathVariable Long taskId) {
//        try {
//            return new ResponseEntity<TaskDto>(taskMapper.mapToTaskDto(service.getTask(taskId)),
//                    HttpStatus.OK);
//        } catch (TaskNotFoundException e) {
//            return new ResponseEntity<>(new TaskDto(0L, "There is no task with id equals to " + taskId, ""),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @GetMapping(value = "{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskId)));
    }


    @DeleteMapping(value = "{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException{
        if(taskId != null)
        service.deleteTask(taskId);

        return ResponseEntity.ok().build();
    }

//    @PutMapping
//    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
//        return ResponseEntity.ok(new TaskDto(1L, "Edited test title", "Test content"));
//    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
        return ResponseEntity.ok().build();
    }
}
