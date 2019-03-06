package br.com.wcruz.erp.services;


import br.com.wcruz.erp.DTO.FornecedorDTO;
import br.com.wcruz.erp.domain.Fornecedor;
import br.com.wcruz.erp.repositories.FornecedorRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> findAll() {
        try {
            return fornecedorRepository.findAll();
        } catch (DataException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Fornecedor find(Long id) {
        try {
            Optional<Fornecedor> forn = fornecedorRepository.findById(id);
            return forn.orElseThrow(() -> new ObjectNotFoundException(id, forn.toString()));
        } catch (DataException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Fornecedor create(Fornecedor obj) {
        try {
            obj.setId(null);
            obj = fornecedorRepository.save(obj);
            return obj;
        } catch (DataException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Fornecedor fromDTO(FornecedorDTO fornecedorDTO) {
        try {
            Fornecedor forn = new Fornecedor(null, fornecedorDTO.getNome(), fornecedorDTO.getEmail(),
                    fornecedorDTO.getCnpj());
            return forn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public Fornecedor update(Fornecedor obj) {
        try {
            Fornecedor newObj = find(obj.getId());
            updateData(newObj, obj);
            return fornecedorRepository.save(newObj);
        } catch (DataException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void updateData(Fornecedor newObj, Fornecedor obj) {
        try {
            newObj.setEmail(obj.getEmail());
            newObj.setNome(obj.getNome());
            newObj.setCnpj(obj.getCnpj());
        } catch (DataException e) {
            e.printStackTrace();
        }

    }


    public void delete(Long id) {
        try {
            find(id);
            fornecedorRepository.deleteById(id);
        } catch (DataException e) {
            e.printStackTrace();
        }

    }
}
