import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Meeting test = new Meeting();
        System.out.println(test.meeting("Fred:Corwill;Wilfred:Corwill;Barney:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill"));

    }
}

class Meeting {

    public static String meeting(String s) {
        StringBuilder result = new StringBuilder();
        String sNew = s.toUpperCase();
        String [] string = sNew.split(";");
        String [] newList = new String[string.length];
        for (int i = 0; i < string.length; i++){
            StringBuilder sbArray = new StringBuilder(string[i]);
            sbArray.insert(0, ", ");
            sbArray.insert(0, sbArray.subSequence(sbArray.indexOf(":")+1, sbArray.length()));
            sbArray.delete(sbArray.indexOf(":"), sbArray.length());
            sbArray.insert(0,"(");
            sbArray.append(")");
            newList[i] = sbArray.toString();
            System.out.println(newList[i]);
        }
        Arrays.sort(newList);
        for (String current : newList){
            result.append(current);
        }

        return result.toString();
    }
}



