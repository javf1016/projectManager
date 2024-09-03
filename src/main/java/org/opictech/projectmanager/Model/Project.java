package org.opictech.projectmanager.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Project {
    private String name;
    private int rating;
    private String category;
    private String projectId;
}
