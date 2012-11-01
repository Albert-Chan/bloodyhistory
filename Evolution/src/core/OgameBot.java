package core;

import network.HttpClient;

public class OgameBot {

        /**
        * 1.make connection to server
        * 2.login
        * 3.read from tactics
        * 4.execute the tactics
        */
        public static void main(String[] args) {
               // make connection to server
               try {
//                   String str = HttpClient.getData("http://www.ogame.tw/", "utf-8");
//                   System.out.println( str);
                     String str = HttpClient.getData();//"http://uni108.ogame.tw/game/reg/login2.php?login=albert&pass=11111111&kid=&v=2" , "utf-8" );
                     System. out.println("======================================================================================" );
                     
                     System. out.println(str);
              } catch (Exception e) {
                      // TODO Auto-generated catch block
                     e.printStackTrace();
              }
               // login
               // read from tactics
               // execute
       }
}
