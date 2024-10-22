package pe.edu.cibertec.app_security_baca_t3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_security_baca_t3.model.bd.Usuario;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleUsuarioService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username);
        return crearUserDetails(usuario, obtenerRolUsuario(usuario.getRol()));
    }

    public List<GrantedAuthority> obtenerRolUsuario(String rol) {
        List<GrantedAuthority> rolesAuth = new ArrayList<>();
        rolesAuth.add(new SimpleGrantedAuthority("ROLE_" + rol));
        return rolesAuth;
    }

    private UserDetails crearUserDetails(Usuario usuario, List<GrantedAuthority> authorityList) {
        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                authorityList
        );
    }
}
