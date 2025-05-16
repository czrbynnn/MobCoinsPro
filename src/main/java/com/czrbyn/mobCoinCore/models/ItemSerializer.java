package com.czrbyn.mobCoinCore.models;

import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.Base64;

public class ItemSerializer {

    public String toBase64(ItemStack item) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream dataOutput = new ObjectOutputStream(outputStream);
        dataOutput.writeObject(item);
        dataOutput.close();
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public ItemStack fromBase64(String base64) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(base64);
        ObjectInputStream dataInput = new ObjectInputStream(new ByteArrayInputStream(data));
        return (ItemStack) dataInput.readObject();
    }
}
