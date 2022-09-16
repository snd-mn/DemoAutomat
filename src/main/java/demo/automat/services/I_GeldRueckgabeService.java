package demo.automat.services;

import demo.automat.entities.Muenze;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface I_GeldRueckgabeService {

    public List<Muenze> getRueckgeld();

}
