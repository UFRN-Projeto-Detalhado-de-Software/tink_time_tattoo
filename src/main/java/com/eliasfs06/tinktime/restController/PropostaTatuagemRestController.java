package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proposta-tatuagem")
public class PropostaTatuagemRestController extends GenericRestController<PropostaTatuagem> {

    private final PropostaTatuagemService propostaTatuagemService;

    public PropostaTatuagemRestController(GenericRepository<PropostaTatuagem> propostaTatuagemGenericRepository,
                                          PropostaTatuagemService propostaTatuagemService) {
        super(propostaTatuagemGenericRepository);
        this.propostaTatuagemService = propostaTatuagemService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<PropostaTatuagem> create(@RequestBody PropostaTatuagem created) {
        try {
            return ResponseEntity.ok(propostaTatuagemService.create(new PropostaTatuagemDTO(created)));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
