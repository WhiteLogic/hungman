package ru.white_logic.utils;

// import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

/**
 * @author WhiteLogic
 */
public class SoundPlayer {

    private final Synthesizer synthesizer;
    private final Receiver receiver;

    public SoundPlayer(int bank, int program) throws MidiUnavailableException {
        synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
//        for (Instrument i : synthesizer.getAvailableInstruments()) {
//            System.out.println(i.getName() + " - " + i.getPatch().getBank() + " : " + i.getPatch().getProgram());
//        }
        synthesizer.getChannels()[0].programChange(bank,program);
        receiver = synthesizer.getReceiver();

    }

    public void playNote(int note, int length) {
        try {
            ShortMessage noteOn = new ShortMessage(ShortMessage.NOTE_ON, note, 127);
            receiver.send(noteOn, -1);
            Thread.sleep(length);

            ShortMessage noteOff = new ShortMessage(ShortMessage.NOTE_OFF, note, 0);
            receiver.send(noteOff, -1);
            Thread.sleep(length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
