package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.understand.Languages;
import lombok.Data;

/**
 * Created by ameen on 07/04/17.
 */
@Data
public class AddRepositoryRequest {
    private String url;
    private Languages language;
}
