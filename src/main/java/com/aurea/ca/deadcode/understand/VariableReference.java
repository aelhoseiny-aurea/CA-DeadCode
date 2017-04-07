package com.aurea.ca.deadcode.understand;

import lombok.Data;

/**
 * Created by ameen on 07/04/17.
 * for parsing {'line': 9, 'colum': 26, 'ref_entity': jcom.aurea.ca.deadcode.understand.ApiTest@7E8057ECD7BB2DE7,
 * 'main_entity': 'ApiTest.projPath', 'kind': 'Define'}    private String mainEntity;
 */
@Data
public class VariableReference {
    private String refEntity;
    private String mainEntity;
    private int line;
    private int column;
    private String kind;

}
