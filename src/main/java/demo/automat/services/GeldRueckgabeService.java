package demo.automat.services;

import demo.automat.entities.Muenze;
import demo.automat.repositories.MuenzenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeldRueckgabeService implements I_GeldRueckgabeService {

    @Autowired
    MuenzenRepository muenzenRepository;

    //TODO einzahlungen mit in den bestand
    @Override
    public List<Muenze> getRueckgeld(BigDecimal Einzahlung, BigDecimal Preis) throws Exception {
        List<Muenze> ret = new ArrayList<Muenze>();
        List<Muenze> bestand = muenzenRepository.findAllOrderdByValueDesc();
        //muenze.id, bestandsAenderung
        Map<Long, Integer> bestandsAenderungen = new HashMap<Long,Integer>();
        BigDecimal initial_differenz = Preis.subtract(Einzahlung);
        BigDecimal current_differenz = initial_differenz;
        int ticks = 0;
        int maxTicks = 50;
        boolean done = false;


        if (initial_differenz.compareTo(BigDecimal.ZERO) > 0) {
            throw new Exception("zu wenig geld!");
        }

        while (current_differenz.compareTo(BigDecimal.ZERO) < 0) {
            ticks++;
            for(Muenze b : bestand)
            {
                Integer bestandsAenderung = bestandsAenderungen.get(b.getId());
                int aktuellerBestand = b.getAnzahl();
                if(bestandsAenderung != null)
                {
                    aktuellerBestand = aktuellerBestand + bestandsAenderung;
                }

                boolean fits = current_differenz.add(b.getWert()).compareTo(BigDecimal.ZERO) > 0;
                fits = fits && b.getAnzahl() > 0;
                if(fits)
                {
                    current_differenz.add(b.getWert());

                    if(bestandsAenderung != null){
                        bestandsAenderung = bestandsAenderung -1;
                        bestandsAenderungen.put(b.getId(), bestandsAenderung);
                    }else{
                        bestandsAenderungen.put(b.getId(), -1);
                    }

                    ret.add(b);
                    break;
                }

                done = current_differenz.compareTo(BigDecimal.ZERO) == 0;
            };
            if(ticks > 50){
                ret = new ArrayList<>();
                break;
            }
        }
        if(done)
        {
            return ret;
        }

        throw new Exception("das ging nicht aus.");

    }


}
