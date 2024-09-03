package org.opictech.projectmanager.Controller;

import org.opictech.projectmanager.Model.Project;
import org.opictech.projectmanager.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/upload")
    public List<Project> uploadCSVFile(@RequestParam("file") MultipartFile file,
                                       @RequestParam int topX,
                                       @RequestParam String category) {
        // Verifica si el archivo está presente
        if (file.isEmpty()) {
            throw new RuntimeException("File doesnt present");
        }
        return projectService.processCSVFile(file, topX, category);
    }

}