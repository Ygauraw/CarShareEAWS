/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Merca
 */
public class Producer {
    //<editor-fold desc="Private members">
    @Resource(mappedName = "jms/CarShareConnctionFactory")
    private static ConnectionFactory connectionfactory;
    @Resource(mappedName = "jms/CarShareQueue")
    private static Queue queue;
    @Resource(mappedName = "jms/CarShareTopic")
    private static Topic topic;
    //</editor-fold>
    
    public void producemessages(List<String> messages){
        MessageProducer mesageProducer;
        TextMessage textMessage;
        try
        {
            Destination dest = (Destination) topic;
            Connection connection = connectionfactory.createConnection();
            Session session = connection.createSession(false, 
                    Session.AUTO_ACKNOWLEDGE);
            mesageProducer = session.createProducer(dest);
            textMessage = session.createTextMessage();

            Iterator i = messages.iterator();
            while(i.hasNext()){
                textMessage.setText((String)i.next());
                mesageProducer.send(textMessage);
            }        

            mesageProducer.close();
            session.close();
            connection.close();
        }
        catch (JMSException e)
        {
          e.printStackTrace();
        }
    }
    
    
//    public static void main(String[] args){
//        final int NUM_MGS;
//        Connection connection = null;
//        if((args.length < 1) || (args.length > 2)){
//            System.err.println("Program takes one or two arguments: <dest_type> [<number-of-messages>]");
//            System.exit(1);
//        }
//        
//        String destType = args[0];
//        System.out.println("Destination type is " + destType);
//        
//        if(!(destType.equals("queue") || destType.equals("topic"))){
//            System.err.println("Argument must be \"queue\" or \"topic\"");
//            System.exit(1);
//        }
//        if(args.length == 2){
//            NUM_MGS = (new Integer(args[1])).intValue();            
//        }else{
//            NUM_MGS = 1;
//        }
//        Destination dest = null;
//        
//        try{
//            if(destType.equals("queue")){
//                dest = (Destination) queue;
//            }else{
//                dest = (Destination) topic;
//            }
//        }catch(Exception e){
//            System.out.println("Error setting destination: " + e.toString());
//            e.printStackTrace();
//            System.exit(1);
//        }
//        
//        try{
//            connection = connectionfactory.createConnection();
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer producer = session.createProducer(dest);
//            TextMessage message = session.createTextMessage();
//            for(int i=0; i<NUM_MGS;i++){
//                message.setText("This is message " + (i+1));
//                System.out.println("Sending message: " + message.getText());
//                producer.send(message);
//            }
//            producer.send(session.createMessage());
//            
//        }catch(JMSException e){
//            System.err.println("Exception occured: " + e.toString());
//        }finally{
//            if(connection != null){
//                try{
//                    connection.close();
//                }catch(JMSException e){
//                    //TODO give some error
//                }
//            }                
//        }
//        
//    }
}
