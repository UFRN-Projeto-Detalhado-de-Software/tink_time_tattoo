package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.Funcionario;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioRestController extends GenericRestController<Funcionario> {
    public FuncionarioRestController(GenericRepository<Funcionario> repository) {
        super(repository);
    }
}
