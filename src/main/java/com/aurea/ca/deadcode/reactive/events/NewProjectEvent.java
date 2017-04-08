package com.aurea.ca.deadcode.reactive.events;

import com.aurea.ca.deadcode.project.Project;
import com.aurea.ca.deadcode.reactive.DeadCodeChannels;
import lombok.Data;

/**
 * Created by ameen on 08/04/17.
 */
@Data
public class NewProjectEvent implements DeadCodeEvent {
    private Project project;
    private Object channel;

    public NewProjectEvent(Project project) {
        this.project = project;
    }

    public DeadCodeChannels getChannel() {
        return DeadCodeChannels.NEW_PROJECT;
    }
}
