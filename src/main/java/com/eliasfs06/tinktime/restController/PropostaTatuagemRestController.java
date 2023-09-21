package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.repository.PropostaTatuagemRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("proposta-tatuagem")
public class PropostaTatuagemRestController extends GenericRestController<PropostaTatuagem> {
    public PropostaTatuagemRestController(PropostaTatuagemRepository repository) {
        super(repository);
    }
}
