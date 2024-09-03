package org.opictech.projectmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.opictech.projectmanager.Model.Project;
import org.opictech.projectmanager.Service.ProjectService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectService = new ProjectService();
    }

    @Test
    void testProcessCSVFileWithEmptyCategory() throws Exception {
        String csvData = "id,rating,category,name\n" +
                "1,85,Science,Project A\n" +
                "2,92,Math,Project B\n";

        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8)));

        List<Project> projects = projectService.processCSVFile(file, 2, "");

        assertEquals(0, projects.size(), "The number of projects should be 0 when category is empty");
    }

    @Test
    void testProcessCSVFileWithNoProjects() throws Exception {
        String csvData = "id,rating,category,name\n" +
                "1,85,Science,Project A\n" +
                "2,92,Math,Project B\n";

        MultipartFile file = Mockito.mock(MultipartFile.class);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8)));

        List<Project> projects = projectService.processCSVFile(file, 2, "History");

        assertEquals(0, projects.size(), "The number of projects should be 0 when no projects match the category");
    }
}
