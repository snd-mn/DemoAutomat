package demo.automat.services;

import demo.automat.entities.Muenze;
import demo.automat.repositories.MuenzenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeldRueckgabeServiceService implements I_GeldRueckgabeService {

    @Autowired
    MuenzenRepository muenzenRepository;

    @Override
    public List<Muenze> getRueckgeld() {
        List<Muenze> ret = new ArrayList<Muenze>();




        return null;
    }


}
