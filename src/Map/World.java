package Map;


import Visualization.DataInputFrame;


import java.awt.*;



public class World {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new DataInputFrame();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

}
