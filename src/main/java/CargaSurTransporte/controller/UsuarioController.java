package CargaSurTransporte.controller;

import CargaSurTransporte.dto.LoginRequest;
import CargaSurTransporte.model.Usuario;
import CargaSurTransporte.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {
        Usuario usuario = service.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = service.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevo) {
        Usuario creado = service.registrar(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}

