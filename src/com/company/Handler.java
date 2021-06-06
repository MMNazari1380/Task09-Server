package com.company;

// ketabkhane ha
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author MMNazari1380
 * @version 1.0
 */
public class Handler implements Runnable
{
    // field
    private final Socket socket;

    // costructor
    public Handler(Socket socket)
    {

        this.socket=socket;

    }

    /**
     * baraye ejraye har client
     */
    @Override
    public void run()
    {

        try {

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String sss = "";

            // halghe baraye chap har string ersali dar server va ersale haman reshte be client
            // va hamchenin baraye etmam barname ba  daryafte "over"
            while (true) {

                String str = dataInputStream.readUTF();
                sss += str;
                sss += "\t";
                dataOutputStream.writeUTF(sss);
                if (str.equals("over"))
                    break;
                System.out.println(str);

            }

            // baraye ersale tamame daryafti ha be client
            dataOutputStream.writeUTF(" tamam ersali ha : ");
            dataOutputStream.writeUTF(sss);
            // bastan socket va stream ha
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();

        }

        // residegi be estesna
        catch ( IOException e)
        {
            e.printStackTrace();
        }

    }

}
