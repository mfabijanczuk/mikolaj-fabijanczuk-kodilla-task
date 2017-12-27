package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        Task task = new Task(666L, "Test title", "test content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(666L, taskDto.getId().longValue());
        assertEquals("Test title", taskDto.getTitle());
        assertEquals("test content", taskDto.getContent());
     }

    @Test
    public void testMapToTaskDto() {
        //Given
        TaskDto taskDto = new TaskDto(666L, "Test title", "test content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(666L, task.getId().longValue());
        assertEquals("Test title", task.getTitle());
        assertEquals("test content", task.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task = new Task(666L, "Test title", "test content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertEquals(1, taskDtos.size());
    }

}
