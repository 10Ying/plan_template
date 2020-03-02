/**
 * Created with IntelliJ IDEA.
 * User: ying
 * Date: 3/2/20
 * Time: 10:10
 * Description: No Description
 */

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Plan {

    static final int[] monthDays = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static final String[] timeline = new String[] {"10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};
    static final int[] totalDays = new int[] {0, 31, 61, 92, 122, 153, 184, 214, 245, 275, 306};

    static String title = "Ying's plan 2020";
    static String newLine = "\n";
    static String dividingLine = "__________________";
    static String nextMonthPlan = "Next Month's Plan:";
    static String nextWeekPlan = "Next Week's Plan:";
    static String thisDayPlan = "Today's Plan:";
    static String thisMonthSummary = "This Month's Summary:";
    static String thisWeekSummary = "This Week's Summary:";
    static String thisDaySummary = "Today's Summary:";
    static String thisYearSummary = "This Year's Summary:";
    static String nextYearPlan = "Next Year's Plan:";


    public static String writeTxt() {

        String s = "";
        String oneDay = "";
        oneDay += thisDayPlan;
        for(String i : timeline) {
            oneDay += i + newLine;
        }
        oneDay += thisDaySummary + dividingLine;

        s += title + newLine + newLine + newLine + newLine;
        s += nextMonthPlan + newLine + dividingLine + dividingLine + dividingLine + newLine;

        for(int i = 1; i < 307; i++) {
            if(isWeekStart(i)) {
                s += newLine + nextWeekPlan + newLine + dividingLine + dividingLine + newLine;
            }
            s += findDate(i);
            if(isWorkingDay(i)) {
                s = workingDayTime(s);
            } else {
                s = weekendDayTime(s);
            }
            if(isWeekEnd(i)) {
                s += newLine + dividingLine + dividingLine + newLine + thisWeekSummary + newLine + dividingLine + dividingLine + newLine;
            }
            if(isMonthStart(i)) {
                s += newLine + dividingLine + dividingLine + newLine +  thisMonthSummary + newLine + dividingLine + dividingLine + dividingLine + newLine;
            }
            if(isMonthStart(i) && i < 276) {
                s += newLine + nextMonthPlan + newLine + dividingLine + dividingLine + dividingLine + newLine;
            }
        }

        s += newLine + newLine + thisYearSummary + dividingLine + dividingLine + dividingLine + dividingLine + newLine;
        s += newLine + newLine + nextYearPlan + dividingLine + dividingLine + dividingLine + dividingLine + newLine;

        return s;
    }

    public static String findDate(int i) {

        String s = "";
        int month = 0;
        int day = 0;
        if(i <= totalDays[1]) {
            month = 3;
        }
        if(i > totalDays[10]) {
            month = 12;
        }
        for(int k = 1; k < 10; k++) {
            if(totalDays[k] < i && i <= totalDays[k + 1]) {
                month = k + 3;
            }
        }

        String s1 = String.valueOf(month);
        if(s1.length() < 2) {
            s1 = "0" + s1;
        }

        for(int j = 0; j < 10; j++) {
            if(totalDays[j] < i && i <= totalDays[j + 1]) {
                day = i - totalDays[j];
            }
        }

        String s2 = String.valueOf(day);
        if(s2.length() < 2) {
            s2 = "0" + s2;
        }

        String s3 = days[(i - 1) % 7];
        s += newLine + s1 + "/" + s2 + "/2020" + "   " + s3 + newLine;
        return s;
    }

    public static String workingDayTime(String s) {

        s += thisDayPlan + newLine + newLine;
        s += "Links: " + newLine + newLine;
        s += "LeetCodes: " + newLine + newLine;
        for(String i : timeline) {
            s += "      " + i + ": " + newLine + newLine;
        }
        s += thisDaySummary + newLine + dividingLine;
        return s;
    }

    public static String weekendDayTime(String s) {
        
        s += "Links: " + newLine + newLine;
        s += "LeetCodes: " + newLine + newLine;
        s += "Others: " + newLine + newLine;
        return s;
    }

    public static boolean isWeekEnd(int i) {
        boolean res = false;
        if(i % 7 == 0) {
            res = true;
        }
        return res;
    }

    public static boolean isWeekStart(int i) {
        boolean res = false;
        if(i % 7 == 1) {
            res = true;
        }
        return res;
    }

    public static boolean isMonthStart(int i) {
        boolean res = false;
        for(int j : totalDays) {
            if(i == j) {
                res = true;
            }
        }
        return res;
    }

    public static boolean isWorkingDay(int i) {
        boolean res = true;
        if(i % 7 == 0 || i % 7 == 1) {
            res = false;
        }
        return res;
    }

    public static void main(String[] args) {
        try {
            FileWriter myFile = new FileWriter("/Users/ying/Desktop/internet/Ying_2020.txt");
            myFile.write(writeTxt());
            myFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}