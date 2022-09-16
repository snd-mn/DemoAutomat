package demo.automat.services;

import demo.automat.entities.Muenze;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface I_GeldRueckgabeService {

    public List<Muenze> getRueckgeld(BigDecimal Einzahlung, BigDecimal Preis) throws Exception;

}
