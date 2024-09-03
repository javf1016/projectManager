package org.opictech.projectmanager.Service;

import com.opencsv.CSVReader;
import org.opictech.projectmanager.Model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    // Método existente para procesar CSV
    public List<Project> processCSVFile(MultipartFile file, int topX, String category) {
        List<Project> projects = new ArrayList<>();
        logger.info("Processing CSV file for category: {} and topX: {}", category, topX);

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            reader.readNext(); // Skip header if present
            while ((line = reader.readNext()) != null) {
                Project project = new Project(line[0], Integer.parseInt(line[1]), line[2], line[3]);
                projects.add(project);
                logger.debug("Added project: {}", project);
            }
        } catch (Exception e) {
            logger.error("Error processing CSV file", e);
        }

        // Filtrar y ordenar los proyectos
        List<Project> rankedProjects = projects.stream()
                .filter(project -> project.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparingInt(Project::getRating).reversed())
                .limit(topX)
                .collect(Collectors.toList());

        logger.info("Ranking completed. Number of projects in ranking: {}", rankedProjects.size());
        return rankedProjects;
    }
}
