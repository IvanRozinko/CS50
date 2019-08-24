import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;




public class test  extends TextProgram {
    public void run(){
        ArrayList<String> list = new ArrayList() ;
        int count = 0;
        while(true){

            list.add("Vasya");
            count++;
            System.out.println(count);
        }
    }
}
