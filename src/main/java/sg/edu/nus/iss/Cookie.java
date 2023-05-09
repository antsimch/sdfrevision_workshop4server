package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private List<String> cookies = new ArrayList<>();

    public void populateCookieList(File cookieFile) throws IOException {

        FileReader fr = new FileReader(cookieFile);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            cookies.add(line);
        } 
        
        br.close();
        fr.close();
    } 

    public String getCookie() {

        Random random = new Random();
        return cookies.get(random.nextInt(cookies.size() - 1));
    }
}
