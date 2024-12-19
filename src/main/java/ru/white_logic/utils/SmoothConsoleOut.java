package ru.white_logic.utils;

import ru.white_logic.Config;

import javax.sound.midi.MidiUnavailableException;

import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * @author WhiteLogic
 */
public class SmoothConsoleOut {

    private static final int SYMBOL_DELAY = 20;
    private static final int CARET_BLINK_DELAY = 20;
    private static final int ALL_STRING_DELAY = 1000;
    private static final char CARET_SYMBOL = '_';

    private static final int CLICK_SOUND_BANK = 128;
    private static final int CLICK_SOUND_PROG = 121;
    private static final int CLICK_SOUND_TONE = 68;
    private static final int CLICK_SOUND_DELAY = 20;

    SoundPlayer player;

    public SmoothConsoleOut() {
        try {
            player = new SoundPlayer(CLICK_SOUND_BANK, CLICK_SOUND_PROG);
        } catch (MidiUnavailableException e) {
            System.err.println("Sounds are not available");
        }
    }

    public void print(String s) {
        try {
            blink();
            blink();
            for (int i = 0; i < s.length(); i++) {
                if (i > Config.FIELD_WIDTH) out.print('\n');
                Thread.sleep(SYMBOL_DELAY);
                out.print("" + s.charAt(i));
                if (player != null) player.playNote(CLICK_SOUND_TONE, CLICK_SOUND_DELAY);
                blink();
            }
            out.print("\n");
            Thread.sleep(ALL_STRING_DELAY);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            exit(1);
        }
    }

    private void blink() throws InterruptedException{
        out.print(CARET_SYMBOL);
        Thread.sleep(CARET_BLINK_DELAY);
        out.print("\b");
        Thread.sleep(CARET_BLINK_DELAY / 10);
    }
}
