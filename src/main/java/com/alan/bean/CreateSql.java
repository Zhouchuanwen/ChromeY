package com.alan.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by alan on 17/1/10.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSql {

    private String colName;

    private String typeName;

    private int colsize;

    private int addition;

    public String getAddition(int addition){
        return addition==1?"DEFAULT NULL":"NOT NULL ";
    }


    public String getTypeName(String typeName){
        if(typeName.equals("LONGVARCHAR")){
            return "VARCHAR";
        }
        return typeName;
    }

}
