package uz.pdp.clickup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String message;
    private Object data;

    public Response(String message) {
        this.message = message;
    }
}
