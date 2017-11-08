package databeam.databeamui;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import java.io.UnsupportedEncodingException;

/**
 * Created by justin on 11/7/17.
 */

public class TestNFC3 extends HostApduService {


    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        switch(bytes[1]){
            case 0x01:
                return "Command 1 Selected!".getBytes();
            case 0x02:

                return strToHex(Testing.fullName);
            default:
        }       return "Incorrect/Missing Command".getBytes();


    }

    @Override
    public void onDeactivated(int i) {
        //Tell the app to display a infobox that mentions the reason for deactivation.
    }

    public byte[] strToHex(String userInput) {
        try {
            return userInput.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Unsupported Encoding".getBytes();
        }
    }
}
