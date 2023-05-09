package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class ServerMain 
{
    public static void main( String[] args ) throws IOException 
    {
        String fileName = "cookie_file.txt";
        int port = 12345;

        if (args.length > 0) {
            fileName = args[0];
        }      

        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();

        File cookieFile = new File(fileName);

        if (!cookieFile.exists()) {
            System.exit(0);;
        }

        Cookie cookie = new Cookie();

        cookie.populateCookieList(cookieFile);

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        String clientInput = "";

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);
        String serverOutput = "";

        while (!clientInput.equalsIgnoreCase("close")) {
            clientInput = dis.readUTF();

            if (clientInput.equalsIgnoreCase("get-cookie")) {
                dos.writeUTF("cookie-text");
                dos.writeUTF(serverOutput);
                dos.flush();
            }
        }

        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();

        socket.close();
        server.close();        
    }
}
