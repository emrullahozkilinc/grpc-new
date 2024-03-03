package com.emr.grpcjava.serializer;

import com.emr.grpcjava.generator.LaptopGenerator;
import com.emr.grpcjava.pb.Laptop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopSerializerTest {

    @Test
    void writeAndReadBinaryFile() {
        String binaryFile = "laptop.bin";
        Laptop laptop1 = LaptopGenerator.generateLaptop();
        LaptopSerializer.writeBinaryFile(laptop1, binaryFile);

        Laptop laptop2 = LaptopSerializer.readBinaryFile(binaryFile);
        assertEquals(laptop1, laptop2);
    }
}