package uz.pdp.clickup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse(String message) {
        this.message = message;
    }
}
