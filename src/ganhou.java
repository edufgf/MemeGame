import java.awt.Color;
import java.awt.Font;
import jplay.*;
public class ganhou {
    public ganhou(){
        
        GameImage ganhou = new GameImage("ganhou.png");
        Time fim = new Time(0,0,10,0,0,false);
        while(true){
            ganhou.draw();
            Main.janela.drawText("Score: "+Integer.toString(Level.score), 305, 550, Color.BLACK, new Font("Base 02",Font.TRUETYPE_FONT, 19));
            if (fim.timeEnded()){
                Main.janela.exit();
            }
            Main.janela.update();
            Main.janela.delay(10);
        }
    }
}
