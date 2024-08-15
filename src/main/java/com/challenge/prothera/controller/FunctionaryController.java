package com.challenge.prothera.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.challenge.prothera.dto.FunctionaryRequestDTO;
import com.challenge.prothera.dto.FunctionaryResponseDTO;
import com.challenge.prothera.service.FunctionaryService;

@RestController
@RequestMapping(value = "/functionarys")
public class FunctionaryController {

    @Autowired
    private FunctionaryService serviceFunctionary;

    @PostMapping
    public ResponseEntity<FunctionaryRequestDTO> insert(@RequestBody FunctionaryRequestDTO dto) {
        serviceFunctionary.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FunctionaryRequestDTO> update(@PathVariable Long id, @RequestBody FunctionaryRequestDTO dto) {
        dto = serviceFunctionary.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        serviceFunctionary.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public FunctionaryResponseDTO findById(@PathVariable Long id) {
        FunctionaryResponseDTO result = serviceFunctionary.findById(id);
        return result;
    }

    @GetMapping
    public List<FunctionaryResponseDTO> findAllFunctionaryWithPerson() {
        List<FunctionaryResponseDTO> result = serviceFunctionary.findAllFunctionaryWithPerson();
        return result;
    }

    // public List<FunctionaryDTO> populateListFunctionary() {
    // List<FunctionaryDTO> listFunctionary = new ArrayList<FunctionaryDTO>();
    // listFunctionary.add(new FunctionaryDTO("Operador", 2009.44D));
    // listFunctionary.add(new FunctionaryDTO("Operador", 2284.38D));
    // listFunctionary.add(new FunctionaryDTO("Coordenador", 9836.14D));
    // listFunctionary.add(new FunctionaryDTO("Diretor", 19199.88D));
    // listFunctionary.add(new FunctionaryDTO("Recepcionista", 2234.68D));
    // listFunctionary.add(new FunctionaryDTO("Operador", 1582.72D));
    // listFunctionary.add(new FunctionaryDTO("Contador", 4071.84D));
    // listFunctionary.add(new FunctionaryDTO("Gerente", 3017.45D));
    // listFunctionary.add(new FunctionaryDTO("Eletricista", 1606.85D));
    // listFunctionary.add(new FunctionaryDTO("Gerente", 2799.93D));
    // return listFunctionary;
    // }

}
