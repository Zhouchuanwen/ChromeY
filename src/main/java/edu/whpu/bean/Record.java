package edu.whpu.bean;

import lombok.Builder;
import lombok.Data;


/**
 * Created by alan on 17/1/6.
 */
@Data
@Builder
public class Record {

    private Long visit;

    private String url;

    private String title;

}
