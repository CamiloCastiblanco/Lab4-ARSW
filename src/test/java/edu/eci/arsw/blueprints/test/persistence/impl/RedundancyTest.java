package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.impl.Redundancy;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.basic.BasicLabelUI;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedundancyTest {

    Point p1, p2, p3, p4, bp1p1, bp1p2, bp1p3, bp1p4;
    Point[] pointsBP1, shouldBePTSBP1,repeatsInBP1PTS, pointsBP2, shouldBePTSBP2, repeatsInBP2PTS;
    Redundancy rd;
    Blueprint bp1, bp2, bp3, bp4;


    @Before
    public void prepareTest(){
        rd = new Redundancy();
        p1 = new Point(5, 1);
        p2 = new Point(0, 0);
        p3 = new Point(5, 1);
        p4 = new Point(0, 0);
       
        bp1p1 = new Point(0, 0);
        bp1p2 = new Point(5, 0);
        bp1p3 = new Point(3, 3);
        bp1p4 = new Point(8, 3);

       

        pointsBP1 = new Point[]{bp1p1, bp1p2, bp1p3, bp1p4, bp1p3, bp1p1, bp1p2};
        repeatsInBP1PTS = new Point[] {bp1p1, bp1p2, bp1p3};
        shouldBePTSBP1 = new Point[]{bp1p4, bp1p3, bp1p1, bp1p2};
        bp1 = new Blueprint("Miguel", "BP1", pointsBP1);       



    }

    @Test
    public void equalsPointsTest(){
        assertTrue(rd.equalsPoints(p1, p3));
        assertTrue(rd.equalsPoints(p2, p4));
        
    }

    @Test
    public void removeRepeatedPointsTest(){
        List<Point> removedPTSBP1 = rd.removeRepeatedPoints(Arrays.asList(repeatsInBP1PTS), Arrays.asList(pointsBP1));
        assertEquals(Arrays.asList(shouldBePTSBP1), removedPTSBP1);        

    }

    @Test
    public void filterPointsTest(){
        assertEquals(new Blueprint("Miguel", "BP1", shouldBePTSBP1), rd.filterPoints(bp1));
       
    }
}
