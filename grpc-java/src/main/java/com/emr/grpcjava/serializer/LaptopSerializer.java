package com.emr.grpcjava.serializer;

import com.emr.grpcjava.pb.Laptop;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LaptopSerializer {

    public static void writeBinaryFile(Laptop laptop, String fileName) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName)){
            laptop.writeTo(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Laptop readBinaryFile(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(fileName)){
            return Laptop.parseFrom(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToJson(Laptop laptop, String fileName) throws InvalidProtocolBufferException {
        JsonFormat.Printer printer = JsonFormat.printer()
                .includingDefaultValueFields()
                .preservingProtoFieldNames();

        String json = printer.print(laptop);

        try (FileOutputStream outputStream = new FileOutputStream(fileName)){
            outputStream.write(json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
