package pe.edu.cibertec.app_security_baca_t3.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.app_security_baca_t3.dto.request.UsuarioRequestDto;
import pe.edu.cibertec.app_security_baca_t3.dto.response.UsuarioResponseDto;
import pe.edu.cibertec.app_security_baca_t3.model.bd.Usuario;
import pe.edu.cibertec.app_security_baca_t3.service.IUsuarioService;
import pe.edu.cibertec.app_security_baca_t3.service.impl.DetalleUsuarioService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final DetalleUsuarioService detalleUsuarioService;
    private final AuthenticationManager authenticationManager;
    private final IUsuarioService usuarioService;
    private final String clave = "Baca_Cibertec_2024";

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> autenticacionUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuarioRequestDto.getUsername(), usuarioRequestDto.getPassword()));
            if(authentication.isAuthenticated()) {
                Usuario authUsuario = usuarioService.obtenerUsuarioPorUsername(usuarioRequestDto.getUsername());
                String token = generarToken(authUsuario);
                return new ResponseEntity<>(
                        UsuarioResponseDto.builder()
                                .id(authUsuario.getId())
                                .username(authUsuario.getUsername())
                                .token(token)
                                .build(),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generarToken(Usuario usuario) {
        List<GrantedAuthority> authorityList = detalleUsuarioService.obtenerRolUsuario(usuario.getRol());
        return Jwts.builder()
                .setId(usuario.getId().toString())
                .setSubject(usuario.getUsername())
                .claim("rol",
                        authorityList.stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS512, clave.getBytes())
                .compact();
    }
}
