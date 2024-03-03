package com.emr.grpcjava.generator;

import com.emr.grpcjava.pb.*;
import com.google.protobuf.Timestamp;

import java.time.Instant;

public class LaptopGenerator {

    public static Laptop generateLaptop() {
        return Laptop.newBuilder()
                .setId("23")
                .setBrand("Apple")
                .setName("Macbook 2019 Pro")
                .setCpu(generateCpu())
                .setMemory(generateMemory())
                .addGpus(generateGpu())
                .addStorages(generateStorageHDD())
                .addStorages(generateStorageSSD())
                .setScreen(generateScreen())
                .setKeyboard(generateKeyboard())
                .setWeightKg(1.33)
                .setPriceUsd(2300.00)
                .setReleaseYear(2019)
                .setUpdatedAt(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).setNanos(Instant.now().getNano()).build())
                .build();
    }

    public static Cpu generateCpu() {
        return Cpu.newBuilder()
                .setBrand("Intel")
                .setName("Core I9")
                .setNumberCores(6)
                .setNumberThreads(10)
                .setMinGhz(2.51)
                .setMaxGhz(3.95)
                .build();
    }


    public static Memory generateMemory() {
        return Memory.newBuilder()
                .setUnit(Memory.Unit.GIGABYTE)
                .setValue(16)
                .build();
    }

    private static Gpu generateGpu() {
        return Gpu.newBuilder()
                .setBrand("AMD")
                .setName("Radeon 3")
                .setMaxGhz(2.7)
                .setMinGhz(1.8)
                .setMemory(generateMemory())
                .build();
    }

    private static Storage generateStorageHDD() {
        return Storage.newBuilder()
                .setDriver(Storage.Driver.HDD)
                .setMemory(Memory.newBuilder().setValue(2).setUnit(Memory.Unit.TERABYTE).build())
                .build();
    }

    private static Storage generateStorageSSD() {
        return Storage.newBuilder()
                .setDriver(Storage.Driver.SSD)
                .setMemory(Memory.newBuilder().setValue(512).setUnit(Memory.Unit.GIGABYTE).build())
                .build();
    }

    private static Screen generateScreen() {
        return Screen.newBuilder()
                .setSizeInch(16)
                .setResolution(Screen.Resolution.newBuilder().setWidth(3284).setHeight(1920).build())
                .setPanel(Screen.Panel.IPS)
                .setMultitouch(true)
                .build();
    }

    private static Keyboard generateKeyboard() {
        return Keyboard.newBuilder()
                .setLayout(Keyboard.Layout.QWERTY)
                .setBacklit(true)
                .build();
    }

    public static void main(String[] args) {
        System.out.println(generateLaptop());
    }
}
