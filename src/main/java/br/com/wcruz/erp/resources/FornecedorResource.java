package br.com.wcruz.erp.resources;


import br.com.wcruz.erp.DTO.FornecedorDTO;
import br.com.wcruz.erp.domain.Fornecedor;
import br.com.wcruz.erp.services.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorResource {

    @Autowired
    private FornecedorService fornecedorService;

    private static Logger LOGGER = LoggerFactory.getLogger(FornecedorResource.class);

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<FornecedorDTO>> fidAll() {
        try {
            List<Fornecedor> list = fornecedorService.findAll();
            List<FornecedorDTO> listDTO = list.stream().map(obj -> new FornecedorDTO(obj)).collect(Collectors.toList());
            LOGGER.info("Lista retornada com sucesso");
            return ResponseEntity.ok().body(listDTO);
        } catch (Exception e) {
            LOGGER.info("Erro ao carregar lista");
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fornecedor> find(@PathVariable Long id) {
        try {
            Fornecedor obj = fornecedorService.find(id);
            LOGGER.info("Fornecedor retornado com sucesso");
            return ResponseEntity.ok().body(obj);
        } catch (Exception e) {
            LOGGER.info("Fornecedor não encontrado");
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<FornecedorDTO> create(@RequestBody FornecedorDTO objDTO) {
        try {
            Fornecedor obj = fornecedorService.fromDTO(objDTO);
            obj = fornecedorService.create(obj);
            /*Neste trecho estou associando para URL o id criado para o novo objeto e já atribuindo o seu direcionamento*/
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            LOGGER.info("Objeto criado com sucesso " + obj.getId());
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            LOGGER.info("Falha ao criar objeto");
            return ResponseEntity.badRequest().body(objDTO);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FornecedorDTO> update(@PathVariable Long id, @RequestBody FornecedorDTO objDTO) {
        try {
            Fornecedor obj = fornecedorService.fromDTO(objDTO);
            obj.setId(id);
            obj = fornecedorService.update(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            LOGGER.info("Objeto editado com sucesso" + obj.getId());
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            LOGGER.info("Falha ao editar objeto");
            return ResponseEntity.badRequest().body(objDTO);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            fornecedorService.delete(id);
            LOGGER.info("Fornecedor deletado com sucesso");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.info("Fornecedor não encontrado");
            return ResponseEntity.notFound().build();
        }
    }

}
