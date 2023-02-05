package OA;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class xrx01 {
    private static final Scanner scan = new Scanner(System.in);

    static String readFile(String fileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try {

            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            Set<String> set = new HashSet<>();

            while (line != null) {
                String[] temp = line.split(" ");
                String tempRes = temp[6];

                System.out.println(temp[8]);
                System.out.println(temp[5]);
                System.out.println(temp[7]);
                if (line.contains("200") || !line.contains("GET") || !line.contains("HTTP")) {
                    line = br.readLine();
                    continue;
                }
//                if (temp[5].isEmpty() || !temp[5].contains("GET")) {
//                    line = br.readLine();
//                    continue;
//                }
//                if (temp[7].isEmpty() || !temp[7].contains("HTTP")) {
//                    line = br.readLine();
//                    continue;
//                }


                System.out.println(tempRes);

                String lastWord = tempRes.substring(tempRes.lastIndexOf("/") + 1);
                if (lastWord.contains(".gif") || lastWord.contains(".GIF")) {
                    if (!set.contains(lastWord)) {
                        sb.append(lastWord);
                        set.add(lastWord);
                        sb.append("\n");
                    }
                }


                line = br.readLine();

            }
            System.out.println(sb.toString());
            return sb.toString();

        } finally {

            br.close();

        }

    }

    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        //filename = "testss.txt";
        filename = scan.next();
        OutputStream os = null;
        String data = readFile(filename);
        String resultFileName = "gifs_" + filename;

        try {

            // below true flag tells OutputStream to append

            os = new FileOutputStream(new File(resultFileName), true);

            os.write(data.getBytes(), 0, data.length());

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                os.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }
}
