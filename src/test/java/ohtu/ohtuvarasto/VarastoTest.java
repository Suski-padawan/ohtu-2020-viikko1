package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto toinenvarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        toinenvarasto=new Varasto(10,2); //testataan toinen konstruktori
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(9, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataLiikaa() {
        varasto.lisaaVarastoon(11);
        //Varaston tilavuus
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(9);
        //Varaston tilavuus
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenLukuEiLisaa() {
        varasto.lisaaVarastoon(-2);
        //Varaston tilavuus
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenLukuEiOta() {
        varasto.otaVarastosta(-2);
        //Varaston tilavuus
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    //Testataan toinen konstruktori
    @Test
    public void konstruktoriLuoVarastonAlkusaldolla() {
        assertEquals(2, toinenvarasto.getSaldo(), vertailuTarkkuus);
    }
    
   @Test
   public void negatiivinenLuoKayttokelvottomanVaraston () {
        Varasto testivarasto = new Varasto(-2);
        
         assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
   }
   
   @Test
   public void negatiivinenAlkuSaldoEiKelpaa () {
        Varasto testivarasto = new Varasto(10,-2);
        
         assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
   }
   @Test
   public void alkusaldoEiSuurempiKuinTilavuus () {
        Varasto testivarasto = new Varasto(10,11);
        
         assertEquals(10, testivarasto.getSaldo(), vertailuTarkkuus);
   }
   
   @Test
   public void negatiivinenTilavuusJaAlkusaldo () {
        Varasto testivarasto = new Varasto(-2,10);
        
         assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
   }
  
   @Test
   public void tulostaaOikein () {
        varasto.lisaaVarastoon(6);
        String teksti="saldo = 6.0, vielä tilaa 4.0";
        assertEquals(teksti, varasto.toString());
   }
  
    
}