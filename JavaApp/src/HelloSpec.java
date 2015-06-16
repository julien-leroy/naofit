/**
 * Created by Xavier on 13/01/15.
 */
import com.aldebaran.qimessaging.Session;
import com.aldebaran.qimessaging.Future;
import com.aldebaran.qimessaging.Application;
import com.aldebaran.qimessaging.helpers.al.ALMotion;
import com.aldebaran.qimessaging.helpers.al.ALRobotPosture;
import com.aldebaran.qimessaging.helpers.al.ALTextToSpeech;

public class HelloSpec {

    public static void main(String[] args) throws Exception {


        Session session = new Session();
        Future<Void> future = session.connect("tcp://127.0.0.1:54704");

        future.get();

        ALTextToSpeech tts = new ALTextToSpeech(session);
        ALMotion motion = new ALMotion(session);
        ALRobotPosture posture = new ALRobotPosture(session);

        tts.setLanguage("French");
        tts.say("Bonjour la ");


       // motion.closeHand("");
      //  motion.walkTo((float) 12, 0f, 0f);
        motion.wakeUp();
        posture.goToPosture("StandInit",(float)1.0);
        motion.rest();
        //motion.WakeUp

    }
}

