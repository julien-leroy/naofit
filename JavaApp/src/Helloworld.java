/**
 * Created by Xavier on 13/01/15.
 */

import com.aldebaran.qimessaging.*;
import com.aldebaran.qimessaging.Object;
import com.aldebaran.qimessaging.helpers.al.ALBehaviorManager;
import com.aldebaran.qimessaging.helpers.al.ALMotion;
import com.aldebaran.qimessaging.helpers.al.ALRobotPosture;
import com.aldebaran.qimessaging.helpers.al.ALSpeechRecognition;


import java.util.ArrayList;


public class Helloworld {
    public static void main(String[] args) throws Exception {

        //Create Sessions
        Session session = new Session();
        //Create Bundle Future
        Future<Void> future = session.connect("tcp://hal.local:9559");
        future.get();
        //Declare Variable Object
        com.aldebaran.qimessaging.Object tts = null;
        com.aldebaran.qimessaging.Object bm = null;
        com.aldebaran.qimessaging.Object listen = null;


        ///****TEST****///VOCAL***///
        static ALMemoryProxy memory = new ALMemoryProxy("tcp://hal.local", "9559");
        static ALSpeechRecognitionProxy recog = new ALSpeechRecognitionProxy("tcp://hal.local", "9559");

        ///***********///*********///
        //Affecte Service
        bm = session.service("ALBehaviorManager");
        tts = session.service("ALTextToSpeech");
        listen = session.service("ALSpeechRecognition");

        //Test la connexion
        boolean ping = tts.<Boolean>call("ping").get() ;

        //SI connecté
        if (ping){
            System.out.print("Ping ok");
            tts.call("say","Bonjour je m'appelle Hal");
            tts.call("say","ibaba, quelle est votre souhait ?");
            System.out.print("First call");


            //**********MOTION***************MOTION***************//
            /*ALMotion motion = new ALMotion(session);
            ALRobotPosture posture = new ALRobotPosture(session);
            motion.wakeUp();
            posture.goToPosture("StandInit", (float) 1.0);
            posture.goToPosture("StandZero",(float)1.0);
            motion.rest();*/
            //***************************************************//

            ArrayList<String> listOfWords = new ArrayList<String>();
            listOfWords.add("Squats");
            listOfWords.add("Pompes");
            listOfWords.add("Fessiers");

            //Listez les activités
            for (int i = 0; i < listOfWords.size(); i++) {
                tts.call("say",listOfWords.get(i));
                System.out.print("list  call");
            }

            //Ecoute le choix et lance l'activité
            ALSpeechRecognition.setVocabulary(listOfWords,false);
            alMemory.subscribeToEvent("WordRecognized", "onWordRecognized::(m)", this);

            bm.call("runBehavior","squats-3e1528");
            System.out.print("call behavior");

        }
        return;
    }
}
