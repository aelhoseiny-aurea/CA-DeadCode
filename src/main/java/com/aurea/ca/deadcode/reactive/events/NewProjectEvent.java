package com.aurea.ca.deadcode.reactive.events;

import com.aurea.ca.deadcode.project.Project;
import lombok.Data;

/**
 * Created by ameen on 08/04/17.
 */
@Data
public class NewProjectEvent {
    private Project project;
}
