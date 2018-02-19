package databeam.databeamui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by justin on 2/19/18.
 */

public class SerializationClass {
    public static byte[] serialization(Object object) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] sObject = null;
        try{
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            sObject = bos.toByteArray();
            bos.close();
        }finally{
            bos.close();
        }
        return sObject;
    }
}
