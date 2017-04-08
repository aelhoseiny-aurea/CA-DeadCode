package com.aurea.ca.deadcode.project;

import com.aurea.ca.deadcode.understand.Languages;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by ameen on 07/04/17.
 */
@Data
public class AddRepositoryRequest {
    @NotEmpty
    private String url;
    @NotNull
    private Languages language;
}
