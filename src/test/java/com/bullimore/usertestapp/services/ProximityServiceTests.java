package com.bullimore.usertestapp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ProximityServiceTests {

    final Double latLondon = 51.51271919;
    final Double lonLondon = -0.09075364;

    @Test
    public void isNorthMoreThan50MilesOutsideLondon(){
        Double latJustOver50ToLondon = 52.27d;
        Double lonJustOver50ToLondon = -0.090753d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustOver50ToLondon, lonJustOver50ToLondon);
        Assertions.assertTrue(distance > 50.0f);
    }

    @Test
    public void isNorthLessThan50MilesOutsideLondon(){
        Double latJustUnder50ToLondon = 52.22d;
        Double lonJustUnder50ToLondon = -0.090753d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance < 50.0f);
    }

    @Test
    public void isLessThan50MilesEastOfLondon(){
        Double latJustUnder50ToLondon = 51.6710832d;
        Double lonJustUnder50ToLondon = 0.8078532d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance < 50.0f);
    }

    @Test
    public void isMoreThan50MilesEastOfLondon(){
        Double latJustUnder50ToLondon = 51.6710832d;
        Double lonJustUnder50ToLondon = 1.37078532d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance > 50.0f);
    }

    @Test
    public void isLessThan50MilesWestOfLondon(){
        Double latJustUnder50ToLondon = 51.6710832d;
        Double lonJustUnder50ToLondon = -0.8078532d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance < 50.0f);
    }

    @Test
    public void isMoreThan50MilesWestOfLondon(){
        Double latJustUnder50ToLondon = 51.6710832d;
        Double lonJustUnder50ToLondon = -1.37078532d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance > 50.0f);
    }
    @Test
    public void isLessThan50MilesSouthOfLondon(){
        Double latJustUnder50ToLondon = 51.010832d;
        Double lonJustUnder50ToLondon = -0.05398d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance < 50.0f);
    }

    @Test
    public void isMoreThan50MilesSouthOfLondon(){
        Double latJustUnder50ToLondon = 50.810832d;
        Double lonJustUnder50ToLondon = -0.5398d;
        ProximityService proximityService = new ProximityServiceImpl();
        Float distance = proximityService.lonLatDifference(latLondon, lonLondon,
                latJustUnder50ToLondon, lonJustUnder50ToLondon);
        Assertions.assertTrue(distance > 50.0f);
    }
}
