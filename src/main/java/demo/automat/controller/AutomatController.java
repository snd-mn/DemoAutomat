package demo.automat.controller;

import demo.automat.entities.Getraenk;
import demo.automat.entities.Muenze;
import demo.automat.repositories.MuenzenRepository;
import demo.automat.runtime.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class AutomatController {
    @Autowired
    MuenzenRepository muenzenRepository;

    @Autowired
    Transaction transaction;

    @PostMapping("/reset")
    public ResponseEntity<String> reset()
    {
        ResponseEntity<String> ret = null;

        transaction.Reset();

        ret = new ResponseEntity<String>("reseted", HttpStatus.OK);
        return ret;
    }

    @PostMapping("/einwurf")
    public ResponseEntity<String> einwurf(@RequestBody Muenze muenze)
    {
        Optional<Muenze> optMuenze = muenzenRepository.findById(muenze.getId());
        muenze = optMuenze.get();

        if(muenze == null)
        {
            new ResponseEntity<String>("keine hosenknoepfe, danke", HttpStatus.FORBIDDEN);
        }

        transaction.receiveEinzahlung(muenze);
        if(transaction.canCommit())
        {
            Getraenk getraenk = transaction.getGetraenkAuswahl();
            transaction.commit();
            transaction.Reset();
            return new ResponseEntity<String>("ausgabe von " + getraenk.getName() + ". guten durst.", HttpStatus.OK);
        }

        return new ResponseEntity<String>("hier fehlt noch was", HttpStatus.OK);
    }

    @PostMapping("/getraenke_wahl")
    public ResponseEntity<String> einwurf(@RequestBody Getraenk getraenk)
    {
        //todo filter getraenk by id, keine getraenke vom mars, die sind noch nicht erfunden
        transaction.receiveOrder(getraenk);
        return new ResponseEntity<String>("danke", HttpStatus.OK);
    }

}
