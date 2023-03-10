package com.test.evp;
/*
This class holds one "rowString" - Java UDTF
create or replace function TF(filepath string, jsonExtrusionElement string)
returns table(data string)
language java
imports = ('@jars/Java-UDTF-1.0-SNAPSHOT.jar')
handler = 'com.snowflake.fda.functions.SplitJSON';
 */
public class JsonData {

    public String data;


    public JsonData(String data) {

        this.data = data;
    }
}