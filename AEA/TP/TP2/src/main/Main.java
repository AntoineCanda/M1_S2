package main;

import detection.Detection;
import sequence.Sequence;

public class Main {

	public static void main(String[] args) {
		Sequence seq = new Sequence();
		String sequence = seq.createSequence(args[0]);
		Detection detection = new Detection(sequence);
		detection.parcourirSequence();
	}

}
