package demo.automat.runtime;

import demo.automat.entities.Getraenk;
import demo.automat.entities.Muenze;
import demo.automat.repositories.GetraenkeRepository;
import demo.automat.repositories.MuenzenRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Transaction {

    @Autowired
    MuenzenRepository muenzenRepository;

    @Autowired
    GetraenkeRepository getraenkeRepository;

    private List<Muenze> Einzahlungen = new ArrayList<>();
    private Getraenk GetraenkAuswahl = null;

    public void receiveEinzahlung(Muenze muenze){
        Einzahlungen.add(muenze);
    }

    /**
     * true -> verfuegbar
     * false -> nicht verfuegbar
     * @param getraenk
     * @return
     */
    public boolean receiveOrder(Getraenk getraenk){
        Getraenk bestand = getraenkeRepository.findById(getraenk.getId()).get();
        boolean ret = bestand.getAnzahl() > 0;
        if(ret)
        {
            GetraenkAuswahl = getraenk;
        }
        return ret;
    }

}
