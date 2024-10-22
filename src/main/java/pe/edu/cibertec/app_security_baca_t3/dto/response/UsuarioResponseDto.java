package pe.edu.cibertec.app_security_baca_t3.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDto {
    private Integer id;
    private String username;
    private String token;
}
