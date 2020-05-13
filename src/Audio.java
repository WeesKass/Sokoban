import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio extends Thread {

    private Clip mainClip;
    private Clip successClip;
    private boolean isRunning;
    private File mainFile = new File("../audio/pacman.wav");
    private File chompFile = new File("../audio/chomp.wav");

    @Override
    public void run() {
        isRunning = true;
        playMain();
    }

    public boolean isPlaying() {
        return isRunning;
    }

    public void soundOnOrOff(){
        if(isRunning) {
            isRunning = false;
            mainClip.stop();
        }else {
            isRunning = true;
            mainClip.loop(Clip.LOOP_CONTINUOUSLY);;
        }
    }
    public void playMain(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mainFile);
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            mainClip = (Clip)AudioSystem.getLine(info);
            mainClip.open(audioInputStream);
            mainClip.setFramePosition(0);
            mainClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void playChomp(){
        if(isRunning){
            try {
                
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(chompFile);
                DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
                successClip = (Clip)AudioSystem.getLine(info);
                successClip.open(audioInputStream);
                successClip.setFramePosition(0);
                successClip.loop(0);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                System.out.println(e);
            }
        }
    }

}
