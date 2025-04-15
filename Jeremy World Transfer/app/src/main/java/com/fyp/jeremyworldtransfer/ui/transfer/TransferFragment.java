package com.fyp.jeremyworldtransfer.ui.transfer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.fyp.jeremyworldtransfer.R;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public class TransferFragment extends Fragment {

    private EditText receiverName1;
    private EditText originCountry1;
    private EditText originalAmount1;
    private EditText receivingCountry1;
    private Button confirmation;
    private static Cipher ecipher;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_transfer, container, false);

        receiverName1 = inflatedView.findViewById(R.id.receiverName);
        originCountry1 = inflatedView.findViewById(R.id.originCountry);
        originalAmount1 = inflatedView.findViewById(R.id.originalAmount);
        receivingCountry1 = inflatedView.findViewById(R.id.receivingCountry);
        confirmation = inflatedView.findViewById(R.id.confirm);

        confirmAction();

        return inflatedView;
        //https://blog.avenuecode.com/android-basics-activities-fragments
    }

    private void sendTransaction(byte[] encryptedMessage) {
        try {
            String ipAddress = "192.168.0.16";
            InetAddress addr = InetAddress.getByName(ipAddress);
            DatagramSocket socket = new DatagramSocket( 80, addr); //https://docs.oracle.com/javase/7/docs/api/java/net/DatagramSocket.html
            Toast.makeText(getContext(), "Connection to service vendor made",
                    Toast.LENGTH_SHORT).show();
            DatagramPacket packet = new DatagramPacket(encryptedMessage, 0, encryptedMessage.length, addr, 80);
            socket.send(packet);  //https://docs.oracle.com/javase/7/docs/api/java/net/DatagramSocket.html
            Toast.makeText(getContext(), "Transaction successfully sent",
                    Toast.LENGTH_SHORT).show();
            socket.close();  //https://docs.oracle.com/javase/7/docs/api/java/net/DatagramSocket.html
        } catch (java.io.IOException e) { //https://www.tutorialspoint.com/javaexamples/net_connected.htm
            Toast.makeText(getActivity(), "Could not send transaction" , Toast.LENGTH_SHORT).show();

        }

    }

    private String encrypt(String message) {
        //https://examples.javacodegeeks.com/core-java/crypto/encrypt-decrypt-string-with-des/
        try {
            byte[] utf8 = message.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            sendTransaction(enc);
        } catch (Exception e) {
            Toast.makeText(getContext(), "An unknown error has occurred",
                    Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void encryptMessage(String receiverName3, String originCountry3, String originalAmount3, String receivingCountry3) {
        //https://examples.javacodegeeks.com/core-java/crypto/encrypt-decrypt-string-with-des/
        try {
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();

            ecipher = Cipher.getInstance("DES");

            ecipher.init(Cipher.ENCRYPT_MODE, key);

            String message = (receiverName3 + originCountry3 + originalAmount3 + receivingCountry3);

            encrypt(message);
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(getContext(), "No Such Algorithm: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } catch (NoSuchPaddingException e) {
            Toast.makeText(getContext(), "No Such Padding: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } catch (InvalidKeyException e) {
            Toast.makeText(getContext(), "Invalid Key: " + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void confirmAction() {
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiverName2 = receiverName1.getText().toString().trim();
                String originCountry2 = originCountry1.getText().toString().trim();
                String originalAmount2 = originalAmount1.getText().toString().trim();
                String receivingCountry2 = receivingCountry1.getText().toString().trim();
                if ((TextUtils.isEmpty(receiverName2)) || (TextUtils.isEmpty(originCountry2)) ||
                        (TextUtils.isEmpty(originalAmount2)) || (TextUtils.isEmpty(receivingCountry2))) {
                    Toast.makeText(getContext(), "One or more required fields are empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                encryptMessage(receiverName2, originCountry2, originalAmount2, receivingCountry2);
            }
        });

    }
}
