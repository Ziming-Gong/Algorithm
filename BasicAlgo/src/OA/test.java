package OA;

import java.util.*;

public class test {
    public static void main(String[] args) {
        String str = "HTTP/1.0";
        String str2 = "\"GET";
        String str3 = "200";
        System.out.println(Integer.valueOf(str3) == 201);
        System.out.println(!str.contains("HTTP"));
        System.out.println(!str2.contains("GET"));
    }

}



