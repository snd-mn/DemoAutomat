package demo.automat.configs;

import demo.automat.entities.Getraenk;
import demo.automat.entities.Muenze;
import demo.automat.repositories.GetraenkeRepository;
import demo.automat.repositories.MuenzenRepository;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Config implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(Config.class);


    @Autowired
    GetraenkeRepository getraenkeRepository;

    @Autowired
    MuenzenRepository muenzenRepository;

    public int getMaxGetraenke(){
        return 100;
    }

    public List<Getraenk> getInitialGetraenke(){
        List<Getraenk> gefuellt = new ArrayList<>();

        Getraenk Cola = new Getraenk();
        Cola.setName("Cola");
        Cola.setPreis(new BigDecimal(1.20));
        Cola.setAnzahl(50);
        Getraenk Veltins = new Getraenk();
        Veltins.setName("Veltins");
        Veltins.setPreis(new BigDecimal(1.20));
        Cola.setAnzahl(49);
        Getraenk Ayawaska = new Getraenk();
        Ayawaska.setName("Ayawaska");
        Ayawaska.setPreis(new BigDecimal(1.20));
        Ayawaska.setAnzahl(1);

        return gefuellt;
    }

    public List<Muenze> getInitialMuenzes(){
        List<Muenze> muenzen = new ArrayList<>();
        Muenze Euro2 = new Muenze();
        Euro2.setName("2-Euro-Muenze");
        Euro2.setWert(new BigDecimal(2));
        Euro2.setAnzahl(25);

        Muenze Euro1 = new Muenze();
        Euro1.setName("1-Euro-Muenze");
        Euro1.setWert(new BigDecimal(1.0));
        Euro1.setAnzahl(25);

        Muenze inDaClub = new Muenze();
        inDaClub.setName("50-Cent-Muenze");
        inDaClub.setWert(new BigDecimal(0.5));
        inDaClub.setAnzahl(25);

        Muenze zwanzigCent = new Muenze();
        zwanzigCent.setName("20-Cent-Muenze");
        zwanzigCent.setWert(new BigDecimal(0.2));
        zwanzigCent.setAnzahl(25);

        Muenze zehnCent = new Muenze();
        zehnCent.setName("10-Cent-Muenze");
        zehnCent.setWert(new BigDecimal(0.1));
        zehnCent.setAnzahl(25);

        Muenze fuenfCent = new Muenze();
        fuenfCent.setName("5-Cent-Muenze");
        fuenfCent.setWert(new BigDecimal(0.05));
        fuenfCent.setAnzahl(25);

        Muenze zweiCent = new Muenze();
        zweiCent.setName("2-Cent-Muenze");
        zweiCent.setWert(new BigDecimal(0.02));
        zweiCent.setAnzahl(25);

        Muenze einCent = new Muenze();
        einCent.setName("1-Cent-Muenze");
        einCent.setWert(new BigDecimal(0.01));
        einCent.setAnzahl(25);

        Muenze hosenknopf = new Muenze();
        einCent.setName("Hosenknopf");
        einCent.setWert(new BigDecimal(0.0));
        einCent.setAnzahl(0);

        return muenzen;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Default Werte in die Datenbank eintragen ... start");
        getraenkeRepository.saveAll(getInitialGetraenke());
        muenzenRepository.saveAll(getInitialMuenzes());
        logger.info("Default Werte in die Datenbank eintragen ... fertig");
    }
}
