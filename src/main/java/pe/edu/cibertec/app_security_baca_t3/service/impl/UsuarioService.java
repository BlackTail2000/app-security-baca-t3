package pe.edu.cibertec.app_security_baca_t3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.app_security_baca_t3.model.bd.Usuario;
import pe.edu.cibertec.app_security_baca_t3.repository.UsuarioRepository;
import pe.edu.cibertec.app_security_baca_t3.service.IUsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
