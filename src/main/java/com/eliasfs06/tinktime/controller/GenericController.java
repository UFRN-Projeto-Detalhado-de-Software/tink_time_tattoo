package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.BaseEntity;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericController<T extends BaseEntity> {

    private final GenericService<T> service;

    public GenericController(GenericRepository<T> repository) {
        this.service = new GenericService<T>(repository) {};
    }

    @GetMapping("")
    public String findAll(Model model, Pageable pageable){
        String name = this.getEntityName();
        Page<T> page = this.service.getPage(pageable);
        model.addAttribute(name, page);
        return name + "/list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model){
        String name = this.getEntityName();
        model.addAttribute("model", service.get(id));
        return name + "/view";
    }

    @GetMapping("/form")
    public ModelAndView getForm() {
        String name = this.getEntityName();
        ModelAndView mv = new ModelAndView(name + "/form");
        try {
            mv.addObject(this.inferGenericType().getDeclaredConstructor().newInstance());
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
                 SecurityException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return mv;
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ModelAndView save(T entity) {
        ModelAndView mv = new ModelAndView();
        this.service.save(entity);

        return mv;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        String name = this.getEntityName();
        return name + "/list";
    }

    protected String getEntityName() {
        return StringUtils.uncapitalize(this.inferGenericType().getSimpleName());
    }

    protected Class<T> inferGenericType() {
        Class<T> class1 = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return class1;
    }
}
