package br.com.wcruz.erp.services;


import br.com.wcruz.erp.domain.Fornecedor;
import br.com.wcruz.erp.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    FornecedorRepository fornecedorRepository;


    public void instantiateDatabase() throws ParseException {

        Fornecedor fornecedor1 = new Fornecedor(null,"teste1","teste@teste.com","123456789");
        Fornecedor fornecedor2 = new Fornecedor(null,"teste2","teste@teste.com","123456789");
        Fornecedor fornecedor3 = new Fornecedor(null,"teste3","teste@teste.com","123456789");

        fornecedorRepository.saveAll(Arrays.asList(fornecedor1,fornecedor2,fornecedor3));

    }


}
