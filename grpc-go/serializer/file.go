package serializer

import (
	"fmt"
	"github.com/golang/protobuf/proto"
	"os"
)

func WriteProtoToBinary(message proto.Message, fileName string) error {
	data, err := proto.Marshal(message)
	if err != nil {
		return fmt.Errorf("couldn't marshal message to binary: %w", err)
	}

	err = os.WriteFile(fileName, data, 0644)

	if err != nil {
		return fmt.Errorf("couldn't write binary data to file: %w", err)
	}

	return nil
}

func ReadProtobufFromBinaryFile(filename string, message proto.Message) error {
	data, err := os.ReadFile(filename)
	if err != nil {
		return fmt.Errorf("cannot read binary data from file: %w", err)
	}

	err = proto.Unmarshal(data, message)
	if err != nil {
		return fmt.Errorf("cannot unmarshal binary to proto message: %w", err)
	}

	return nil
}
