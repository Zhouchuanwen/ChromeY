package edu.whpu.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

/**
 * Created by jianghailong on 16/6/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper {

    public static final Wrapper SUCCESS = Wrapper.builder().code(0).msg("SUCCESS").build();

    public static final Wrapper ERROR = Wrapper.builder().code(-1).msg("ERROR").build();

    private int code;

    private String msg;

    private String description;

    private Object data;

    public Wrapper(int code, String msg) {
        this(code, msg, null);
    }

    public Wrapper(int code, String msg, String description) {
        this(code, msg, description, null);
    }

    public String json() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("code", code);
        jsonObject.putOnce("msg", msg);
        return jsonObject.toString();
    }
}
