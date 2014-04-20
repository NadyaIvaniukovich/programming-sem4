package by.bsu.ivanyukovich.entertainment.io;

import by.bsu.ivanyukovich.entertainment.criteria.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.bsu.ivanyukovich.entertainment.place.CompanionType.parseCompanions;

/**
 * Created by Hope on 3/26/14.
 */
public class CriteriaReader {
    private String fileName;

    public CriteriaReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Criteria> read() throws IOException, InvalidCriteriaException {
        try(FileInputStream in = new FileInputStream(fileName);
            Scanner scanner = new Scanner(in)
        ){
            List<Criteria> criteriaList = new ArrayList<>();
            while (scanner.hasNext()){
                String[] row = scanner.nextLine().split(",");
                assert row.length > 1;
                switch (row[0]){
                    case "AgeLimit": criteriaList.add(new AgeLimitCriteria(Integer.parseInt(row[1]))); break;
                    case "Companions": criteriaList.add(new CompanionsCriteria(parseCompanions(row[1]))); break;
                    case "Cost": criteriaList.add(new CostCriteria(Integer.parseInt(row[1]))); break;
                    case "StayTimeLimited": criteriaList.add(new StayTimeLimitedCriteria(row[1].equals("true"))); break;
                    case "WorkTimeLimit": criteriaList.add(new WorkTimeLimitCriteria(Integer.parseInt(row[1]), Integer.parseInt(row[2]))); break;
                    default: throw new InvalidCriteriaException("Type of criteria " + row[0] + " is unknown.");
                }
            }
            return criteriaList;

        }
    }
}
