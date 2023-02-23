package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {


    public static void main(String a[]) throws BlueprintNotFoundException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = appContext.getBean(BlueprintsServices.class);
        Point[] pts = new Point[] { new Point(0, 0), new Point(10, 10), new Point(10, 10), new Point(11, 10),
                new Point(15, 10), new Point(15, 10), new Point(0, 0) };
        Blueprint currentBp = new Blueprint("Camilo", "casa#38", pts);
        bps.addNewBlueprint(currentBp);
        bps.addNewBlueprint(new Blueprint("Cami", "casa#35"));
        bps.addNewBlueprint(new Blueprint("Francisco", "casa#11"));
        bps.addNewBlueprint(new Blueprint("Camilo", "casa#8"));
        Set<Blueprint> authorBP = bps.getBlueprintsByAuthor(currentBp.getAuthor());
        System.out.println("getCurrentBluePrintByAuthor");
        for (Blueprint b : authorBP) {
            System.out.println(b);
        }
        System.out.println("getCurrentBluePrint");
        System.out.println(bps.getBlueprint(currentBp.getAuthor(), currentBp.getName()));
        ((ClassPathXmlApplicationContext) appContext).close();


        System.out.println("Get blueprint con redundancy");
        Point[] ptsFilter = new Point[]{new Point(10, 10), new Point(10, 10), new Point(10,10), new Point(10,10)};
        bps.addNewBlueprint(new Blueprint("Sebastian", "casa#38", ptsFilter));
        Blueprint blueprint1 = bps.getBlueprint("Sebastian","casa#38");
        for (Point p: blueprint1.getPoints()) {
            System.out.println("Punto: " + p);
        }

        System.out.println("Get blueprint por autor con redundancy");
        Point[] ptsFilter1 = new Point[]{new Point(10, 11), new Point(11, 10), new Point(11,10), new Point(10, 11)};
        Point[] ptsFilter2 = new Point[]{new Point(11, 10), new Point(11, 10), new Point(10,10)};
        bps.addNewBlueprint(new Blueprint("David", "casa#77", ptsFilter1));
        bps.addNewBlueprint(new Blueprint("David", "casa#78", ptsFilter2));
        Set<Blueprint> setbp = bps.getBlueprintsByAuthor("Angel");
        for (Blueprint bp: setbp) {
            System.out.println("Blueprint " + bp.getName());
            for (Point p: bp.getPoints()) {
                System.out.println("Punto: " + p);
            }
        }

        System.out.println("Get blueprint con undersampling");
        Point[] ptsFilterUS = new Point[]{new Point(11, 11), new Point(12, 12), new Point(13,13), new Point(14,14)};
        bps.addNewBlueprint(new Blueprint("Yeison", "casa#85", ptsFilterUS));
        Blueprint blueprintunder = bps.getBlueprint("Yeison","casa#85");
        for (Point p: blueprintunder.getPoints()) {
            System.out.println("Punto: " + p);
        }

        System.out.println("Get blueprint por autor con undersampling");
        Point[] ptsFilter1US = new Point[]{new Point(13, 13), new Point(14,14), new Point(15,15)};
        Point[] ptsFilter2US = new Point[]{new Point(11, 10), new Point(10,12), new Point(10,11)};
        bps.addNewBlueprint(new Blueprint("Marquez", "casa#99", ptsFilter1US));
        bps.addNewBlueprint(new Blueprint("Maqrquez", "casa#98", ptsFilter2US));
        Set<Blueprint> bpus = bps.getBlueprintsByAuthor("Bazurto");
        for (Blueprint bp: bpus) {
            System.out.println("Blueprint " + bp.getName());
            for (Point p: bp.getPoints()) {
                System.out.println("Punto: " + p);
            }
        }

    }

}
