package demo.automat.runtime;

import demo.automat.entities.Getraenk;
import demo.automat.entities.Muenze;
import demo.automat.repositories.GetraenkeRepository;
import demo.automat.repositories.MuenzenRepository;
import demo.automat.services.GeldRueckgabeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Transaction {

    @Autowired
    MuenzenRepository muenzenRepository;

    @Autowired
    GetraenkeRepository getraenkeRepository;

    @Autowired
    GeldRueckgabeService geldRueckgabeService;


    private List<Muenze> Einzahlungen = new ArrayList<>();
    private List<Muenze> rueckgeld = new ArrayList<>();
    private Getraenk GetraenkAuswahl = null;

    public void receiveEinzahlung(Muenze muenze) {
        Einzahlungen.add(muenze);
    }

    /**
     * true -> verfuegbar
     * false -> nicht verfuegbar
     *
     * @param getraenk
     * @return
     */
    public boolean receiveOrder(Getraenk getraenk) {
        Getraenk bestand = getraenkeRepository.findById(getraenk.getId()).get();
        boolean ret = bestand.getAnzahl() > 0;
        if (ret) {
            GetraenkAuswahl = getraenk;
        }
        return ret;
    }

    public boolean canCommit() {
        if(Einzahlungen.isEmpty())
        {
            //hier gibts nix für umsonst
            return false;
        }

        BigDecimal einzahlung = BigDecimal.ZERO;
        Einzahlungen.forEach((e) -> {
            einzahlung.add(e.getWert().multiply(new BigDecimal(e.getAnzahl())));
        });

        if(GetraenkAuswahl == null){
            //wer nicht weiss war er will...
            return false;
        }

        BigDecimal preis = GetraenkAuswahl.getPreis();

        try {
            rueckgeld = geldRueckgabeService.getRueckgeld(einzahlung, preis);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void Reset(){
        Einzahlungen = new ArrayList<>();
        rueckgeld = new ArrayList<>();
        GetraenkAuswahl = null;
    }

    @Transactional
    public boolean commit() {
        boolean canCommit = canCommit();
        boolean commited = false;
        if(canCommit())
        {
            //TODO bestandsaenderungen


            commited = true;
        }

        return commited;
    }

}
