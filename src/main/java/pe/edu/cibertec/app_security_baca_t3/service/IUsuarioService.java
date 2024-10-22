package pe.edu.cibertec.app_security_baca_t3.service;

import pe.edu.cibertec.app_security_baca_t3.model.bd.Usuario;

public interface IUsuarioService {
    Usuario obtenerUsuarioPorUsername(String username);
}
