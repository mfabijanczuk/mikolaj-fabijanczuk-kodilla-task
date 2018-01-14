package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyListOfTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(123L, "Test", "Test content"));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(123L, "Test", "Test content"));

        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(123)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].content", is("Test content")));
    }

    @Test
    public void testGetTask() throws Exception {
        // Given
        Optional task = Optional.of(new Task(123l, "Test  getTask", "Test"));
        Task task1 = new Task(123l, "Test getTask", "Test");
        TaskDto taskDto = new TaskDto(123l, "Test getTaskDto", "Test");

        when(dbService.getTaskFromId(anyLong())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/tasks/1")
                .param("taskId","1")        // skąd ja to wziąłem??
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.title", is("Test getTaskDto")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(123L, "Test", "Test task");

        doNothing().when(dbService).deleteById(taskDto.getId());

        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto updatedTask = new TaskDto(123L, "Test update", "test update content");
        Task task = new Task(123L, "Test update", "test update content");

        when(taskMapper.mapToTask((ArgumentMatchers.any(TaskDto.class)))).thenReturn(task);
        when(dbService.saveTask((ArgumentMatchers.any(Task.class)))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(updatedTask);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(updatedTask);

        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(123)))
                .andExpect(jsonPath("$.title", is("Test update")))
                .andExpect(jsonPath("$.content", is("test update content")));
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        Task task = new Task(123l, "Test create task", "Test");
        TaskDto taskDto = new TaskDto(1l, "Test create taskDto", "Test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        // When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}