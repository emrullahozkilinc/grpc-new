package main

import (
	"fmt"
	"google.golang.org/protobuf/types/known/timestamppb"
	"grpc-go/protobuf"
	"grpc-go/serializer"
)

func NewKeyboard() *protobuf.Keyboard {
	keyboard := &protobuf.Keyboard{
		Layout:  protobuf.Keyboard_QWERTY,
		Backlit: true,
	}

	return keyboard
}

func NewCpu() *protobuf.Cpu {

	cpu := &protobuf.Cpu{
		Brand:         "Intel",
		Name:          "Core I5",
		NumberCores:   4,
		NumberThreads: 8,
		MinGhz:        2.5,
		MaxGhz:        4.7,
	}

	return cpu
}

func NewGpu() *protobuf.Gpu {

	memory := &protobuf.Memory{
		Value: 8,
		Unit:  protobuf.Memory_GIGABYTE,
	}

	gpu := &protobuf.Gpu{
		Brand:  "Intel",
		Name:   "Core I5",
		MinGhz: 2.3,
		MaxGhz: 5.6,
		Memory: memory,
	}

	return gpu
}

func NewRam() *protobuf.Memory {
	ram := &protobuf.Memory{
		Value: 16,
		Unit:  protobuf.Memory_GIGABYTE,
	}

	return ram
}

func NewSSD() *protobuf.Storage {
	ssd := &protobuf.Storage{
		Driver: protobuf.Storage_SSD,
		Memory: &protobuf.Memory{
			Value: 256,
			Unit:  protobuf.Memory_GIGABYTE,
		},
	}

	return ssd
}

func NewHDD() *protobuf.Storage {
	hdd := &protobuf.Storage{
		Driver: protobuf.Storage_HDD,
		Memory: &protobuf.Memory{
			Value: 2,
			Unit:  protobuf.Memory_TERABYTE,
		},
	}

	return hdd
}

// NewScreen returns a new sample Screen
func NewScreen() *protobuf.Screen {
	screen := &protobuf.Screen{
		SizeInch: 19,
		Resolution: &protobuf.Screen_Resolution{
			Width:  1920,
			Height: 1080,
		},
		Panel:      protobuf.Screen_IPS,
		Multitouch: true,
	}

	return screen
}

// NewLaptop returns a new sample Laptop
func NewLaptop() *protobuf.Laptop {
	laptop := &protobuf.Laptop{
		Id:       "2",
		Brand:    "Acer",
		Name:     "Aspire A3",
		Cpu:      NewCpu(),
		Memory:   NewRam(),
		Gpus:     []*protobuf.Gpu{NewGpu()},
		Storages: []*protobuf.Storage{NewSSD(), NewHDD()},
		Screen:   NewScreen(),
		Keyboard: NewKeyboard(),
		Weight: &protobuf.Laptop_WeightKg{
			WeightKg: 1.85,
		},
		PriceUsd:    2950,
		ReleaseYear: 2017,
		UpdatedAt:   timestamppb.Now(),
	}

	return laptop
}

func main() {

	newLaptop := NewLaptop()

	err := serializer.WriteProtoToBinary(newLaptop, "lap")

	if err != nil {
		fmt.Print("error occured while writing...")
		fmt.Print(err)
		return
	}

	laptop2 := &protobuf.Laptop{}

	err = serializer.ReadProtobufFromBinaryFile("lap", laptop2)

	if err != nil {
		fmt.Print("error occured while reading...")
		fmt.Print(err)
		return
	}
}
