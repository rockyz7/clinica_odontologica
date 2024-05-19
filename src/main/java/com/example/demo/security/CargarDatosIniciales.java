package com.example.demo.security;

import com.example.demo.Model.Usuario;
import com.example.demo.Model.UsuarioRole;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passwordCifrada = cifrador.encode("digital");
        String passwordCifrada2 = cifrador.encode("digitalAdmin");
        usuarioRepository.save(new Usuario("Rocky", "Montero", "rocky@user.com", passwordCifrada, UsuarioRole.ROLE_USER));
        usuarioRepository.save(new Usuario("Rocky", "Montero", "rocky@admin.com", passwordCifrada2, UsuarioRole.ROLE_ADMIN));
    }


}
